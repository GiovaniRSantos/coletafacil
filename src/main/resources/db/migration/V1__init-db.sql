-- Criar o schema dentro do banco de dados se não existir
DO $$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = 'coletafacil') THEN
CREATE SCHEMA coletafacil;
END IF;
END $$;

-- Definir o schema como padrão para a sessão atual
SET search_path TO coletafacil;

-- Criação das tabelas
CREATE TABLE IF NOT EXISTS Residuo (
                                       id_residuo SERIAL PRIMARY KEY,
                                       tipo_residuo VARCHAR(255) NOT NULL,
    descricao_residuo VARCHAR(255),
    cor_coleta VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS LocalColeta (
                                           id_local_coleta SERIAL PRIMARY KEY,
                                           endereco VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    tipo_local VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS BaseDescarte (
                                            id_base_descarte SERIAL PRIMARY KEY,
                                            nome_base VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    tipo_descarte VARCHAR(255) NOT NULL,
    capacidade_tonelada FLOAT NOT NULL,
    horario_funcionamento VARCHAR(255) NOT NULL,
    responsavel VARCHAR(255),
    telefone VARCHAR(255),
    observacoes VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS Coleta (
                                      id_coleta SERIAL PRIMARY KEY,
                                      data_coleta TIMESTAMP NOT NULL,
                                      id_local_coleta INT NOT NULL,
                                      id_residuo INT NOT NULL,
                                      id_base_descarte INT,
                                      quantidade_residuo FLOAT NOT NULL,
                                      observacoes VARCHAR(255),
    FOREIGN KEY (id_local_coleta) REFERENCES LocalColeta(id_local_coleta),
    FOREIGN KEY (id_residuo) REFERENCES Residuo(id_residuo),
    FOREIGN KEY (id_base_descarte) REFERENCES BaseDescarte(id_base_descarte)
    );

-- Definir o schema como padrão para a sessão atual
SET search_path TO coletafacil;

-- Verificar se o tipo ENUM já existe antes de criar
DO $$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'status') THEN
CREATE TYPE status AS ENUM ('Ativo', 'Cancelada', 'Finalizado');
END IF;
END $$;

CREATE TABLE IF NOT EXISTS AgendamentoColetas (
                                                  id_agendamento BIGSERIAL PRIMARY KEY,
                                                  id_coleta INT NOT NULL,  -- Relacionando com a tabela Coleta
                                                  observacoes TEXT, -- Observações opcionais
                                                  status status NOT NULL DEFAULT 'Ativo', -- Novo campo status
                                                  FOREIGN KEY (id_coleta) REFERENCES Coleta(id_coleta)  -- Estabelecendo a relação com a tabela Coleta
    );

-- Função para popular as tabelas com dados de exemplo
CREATE OR REPLACE FUNCTION PopularDadosExemplo() RETURNS VOID AS $$
BEGIN
    -- Popular tabela Residuo
INSERT INTO Residuo (tipo_residuo, descricao_residuo, cor_coleta) VALUES
                                                                      ('Plástico', 'Embalagens de plástico', 'Azul'),
                                                                      ('Papel', 'Papéis e papelões', 'Amarelo'),
                                                                      ('Vidro', 'Vidros em geral', 'Verde')
    ON CONFLICT DO NOTHING;

-- Popular tabela LocalColeta
INSERT INTO LocalColeta (endereco, bairro, cidade, estado, cep, tipo_local) VALUES
                                                                                ('Rua das Flores, 123', 'Centro', 'São Paulo', 'SP', '01234-567', 'Ponto de Coleta'),
                                                                                ('Av. das Árvores, 456', 'Jardins', 'Rio de Janeiro', 'RJ', '23456-789', 'Estação de Reciclagem'),
                                                                                ('R. das Pedras, 789', 'Praia', 'Salvador', 'BA', '34567-890', 'Centro de Descarte')
    ON CONFLICT DO NOTHING;

-- Popular tabela BaseDescarte
INSERT INTO BaseDescarte (nome_base, endereco, cidade, estado, cep, tipo_descarte, capacidade_tonelada, horario_funcionamento, responsavel, telefone, observacoes) VALUES
                                                                                                                                                                       ('Recicla Mais', 'Av. das Flores, 789', 'São Paulo', 'SP', '02345-678', 'Reciclagem', 50.0, 'Seg-Sex 08:00-18:00', 'João Silva', '(11) 98765-4321', 'Aceita apenas plástico e papel'),
                                                                                                                                                                       ('Eco Center', 'Rua das Árvores, 321', 'Rio de Janeiro','RJ', '04567-890', 'Reciclagem', 100.0, 'Seg-Sáb 09:00-20:00', 'Maria Oliveira', '(21) 87654-3210', 'Aceita todos os tipos de resíduos'),
                                                                                                                                                                       ('Descarte Consciente', 'Av. da Praia, 456', 'Salvador', 'BA', '05678-901', 'Descarte', 200.0, 'Seg-Dom 07:00-22:00', 'Carlos Santos', '(71) 76543-2109', 'Recolhe resíduos perigosos')
    ON CONFLICT DO NOTHING;

-- Popular tabela Coleta
INSERT INTO Coleta (data_coleta, id_local_coleta, id_residuo, id_base_descarte, quantidade_residuo, observacoes) VALUES
                                                                                                                     ('2024-05-27 10:00:00', 1, 1, 1, 10.5, 'Primeira coleta'),
                                                                                                                     ('2024-05-28 11:00:00', 2, 2, 2, 20.0, 'Segunda coleta'),
                                                                                                                     ('2024-05-29 12:00:00', 3, 3, 3, 30.0, 'Terceira coleta')
    ON CONFLICT DO NOTHING;

-- Popular tabela AgendamentoColetas
INSERT INTO AgendamentoColetas (id_coleta, observacoes, status) VALUES
                                                                    (1, 'Primeiro agendamento de coleta', 'Ativo'),
                                                                    (2, 'Segundo agendamento de coleta', 'Ativo'),
                                                                    (3, 'Terceiro agendamento de coleta', 'Finalizado')
    ON CONFLICT DO NOTHING;
END;
$$ LANGUAGE plpgsql;

-- Chamar a função para popular os dados
SELECT PopularDadosExemplo();
