import pandas as pd
import matplotlib.pyplot as plt
import os

# Função para carregar dados de arquivos CSV
def carregar_dados(diretorio):
    dados = []
    for pivô in ['first', 'last', 'random', 'median']:
        for arranjo in ['crescente', 'decrescente', 'quase', 'random']:
            for n in [100, 1000, 10000]:
                arquivo = f"{diretorio}/{pivô}/{arranjo}/{pivô}-{arranjo}-{n}.csv"
                if os.path.exists(arquivo):
                    df = pd.read_csv(arquivo)
                    df['pivô'] = pivô
                    df['arranjo'] = arranjo
                    df['n'] = n
                    dados.append(df)
                else:
                    print(f"Arquivo não encontrado: {arquivo}")
    return pd.concat(dados, ignore_index=True) if dados else pd.DataFrame()

# Carregar os dados
diretorio = '/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/analise'
dados = carregar_dados(diretorio)
dados.columns = dados.columns.str.strip()

# Criar a estrutura de pastas
output_dir = '/home/lucas/Desktop/PucMinas/2024-2/AEDS2/Labs/Lab06/output'
os.makedirs(output_dir, exist_ok=True)

# Criar subpastas para os resultados
for pivô in ['first', 'last', 'random', 'median']:
    arranjos_dir = os.path.join(output_dir, pivô)
    os.makedirs(arranjos_dir, exist_ok=True)

# Criar uma pasta para os gráficos dos pivôs
pivos_dir = os.path.join(output_dir, 'pivos')
os.makedirs(pivos_dir, exist_ok=True)

# Criar pastas para cada tipo de arranjo dentro da pasta "pivos"
for arranjo in ['crescente', 'decrescente', 'quase', 'random']:
    os.makedirs(os.path.join(pivos_dir, arranjo), exist_ok=True)

# Plotar gráficos de comparações e movimentações por arranjo
for arranjo in ['crescente', 'decrescente', 'quase', 'random']:
    for n in [100, 1000, 10000]:
        subset = dados[(dados['arranjo'] == arranjo) & (dados['n'] == n)]  # Filtrar pelos dados corretos

        if subset.empty:
            print(f"Nenhum dado disponível para o arranjo {arranjo} com {n} elementos.")
            continue

        plt.figure(figsize=(12, 6))

        # Agrupar os dados por pivô e calcular a média
        comparacoes = subset.groupby('pivô')['comparacoes'].mean()
        movimentacoes = subset.groupby('pivô')['movimentacoes'].mean()

        # Gráfico com dois eixos y
        ax1 = plt.gca()

        # Eixo para comparações
        ax1.set_xlabel('Tipo de Pivô', fontsize=14)
        ax1.set_ylabel('Comparações', fontsize=14, color='#4CA1A3')
        ax1.bar(comparacoes.index, comparacoes, color='#4CA1A3', alpha=0.7, width=0.4, label='Comparações')
        ax1.tick_params(axis='y', labelcolor='#4CA1A3')

        # Eixo y secundário para movimentações
        ax2 = ax1.twinx()
        ax2.set_ylabel('Movimentações', fontsize=14, color='#F08030')
        ax2.bar([x + 0.4 for x in range(len(movimentacoes))], movimentacoes, color='#F08030', alpha=0.7, width=0.4, label='Movimentações')
        ax2.tick_params(axis='y', labelcolor='#F08030')

        # Definir título apropriado
        if arranjo == 'quase':
            titulo = 'Quase Ordenado'
        elif arranjo == 'random':
            titulo = 'Aleatório'
        else:
            titulo = arranjo.capitalize()

        # Configurações do gráfico
        plt.title(f'Análise do Quicksort para Arranjo {titulo}\nComparações e Movimentações para {n} elementos', fontsize=16)
        ax1.set_xticks([x + 0.2 for x in range(len(comparacoes))])
        ax1.set_xticklabels(comparacoes.index, fontsize=12)
        plt.tight_layout()

        # Salvar o gráfico na pasta correspondente dentro de "pivos"
        plt.savefig(os.path.join(pivos_dir, arranjo, f'grafico_{arranjo}_{n}.png'))
        plt.close()  # Fechar a figura
        print(f"Gráfico salvo: Arranjo {arranjo} - {n} elementos")

# Gráficos de comparação por arranjo e pivô (modificados para gráfico de linhas)
for pivô in ['first', 'last', 'random', 'median']:
    for n in [100, 1000, 10000]:
        subset = dados[(dados['pivô'] == pivô) & (dados['n'] == n)]

        if subset.empty:
            print(f"Nenhum dado disponível para o pivô {pivô} com {n} elementos.")
            continue

        # Verificar se existem pelo menos 30 testes
        if len(subset) < 30:
            print(f"Número insuficiente de testes para o pivô {pivô} e {n} elementos.")
            continue

        plt.figure(figsize=(12, 6))

        # Ordenar pelo índice para garantir a sequência dos testes
        subset = subset.sort_index()

        # Criar um eixo X de 1 a 30
        x_values = list(range(1, 31))

        # Plotar comparações
        plt.plot(x_values, subset['comparacoes'][:30], marker='o', linestyle='-', color='#4CA1A3', label='Comparações')

        # Plotar movimentações
        plt.plot(x_values, subset['movimentacoes'][:30], marker='o', linestyle='-', color='#F08030', label='Movimentações')

        # Configurações do gráfico
        plt.xlabel('Teste', fontsize=14)
        plt.ylabel('Quantidade', fontsize=14)
        plt.title(f'Quicksort: Pivô {pivô} - Comparações e Movimentações\n{n} elementos', fontsize=16)
        plt.legend()
        plt.grid(True)
        plt.tight_layout()

        # Ajustar a escala do eixo Y automaticamente
        plt.yscale('linear')

        # Salvar o gráfico diretamente na pasta do pivô
        plt.savefig(os.path.join(output_dir, pivô, f'grafico_{pivô}_{n}.png'))
        plt.close()  # Fechar a figura
        print(f"Gráfico salvo: Pivô {pivô} - {n} elementos")

print("Processo concluído.")
