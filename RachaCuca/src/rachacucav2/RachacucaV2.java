package rachacucav2;

import java.util.Random;
import java.util.Scanner;

public class RachacucaV2 {
//Codigo feito por Pedro Polido e Luis Filipe Leao.    

    public static void ImprimeTabuleiro(int pastilhas[][]) {                                 //Essa funcao recebe as pecas do tabuleiro para
        System.out.println(" ------- ");                                                   //que possam ser impressas de acordo com o funcionamento      
        for (int lin = 0; lin < pastilhas.length; lin++) {                                   //da funcao.
            for (int col = 0; col < pastilhas[0].length; col++) {                            //Esse laco passa por todas as pecas do tabuleiro e as imprime    
                if (col == 0) {                                                              //de acordo com o design feito. Ao passar pela posicao de valor 0,      
                    System.out.print("| ");                                                //o laco imprime um espaco.
                }
                if (pastilhas[lin][col] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(pastilhas[lin][col] + " ");
                }
                if (col == 2) {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println(" ------- \n");
    }

    public static int[] Movimento(int m, int zerolinha, int zerocoluna) {                    //A funcao movimento recebe o o numero sorteado referente ao movimento que
                                                                                             //a peca ira fazer e a posicao de linha e coluna do zero (espaco vazio). 
        int movcoluna = 0;
        int movlinha = 0;
        int resultado[] = new int[2];

        if (m == 1) {
            movcoluna = 0;
            movlinha = -1;
        }

        if (m == 2) {
            movcoluna = 0;
            movlinha = +1;
        }

        if (m == 3) {
            movcoluna = -1;
            movlinha = 0;
        }

        if (m == 4) {
            movcoluna = +1;
            movlinha = 0;
        }

        resultado[0] = zerocoluna + movcoluna;                                               //Os valores alocados nas variaveis de acordo com o movimento serao adicionados
        resultado[1] = zerolinha + movlinha;                                                 //aos indices do vetor para que a funcao possa retorna-los.

        return (resultado);
    }

    public static void Jogar(int tabuleiro[][], int move, int zerolinha, int zerocoluna) {   // Função destinada ao processo de jogabilidade do código, nela
        Scanner entrada = new Scanner(System.in);                                      // podemos observar a presença de um contador de movimentos e um
        ImprimeTabuleiro(tabuleiro);                                                 // verificador para constar se a peça é adjacente ao espaço vazio.
        System.out.println("Movimento: " + move);                                            // Através desse verificador podemos realizar os movimentos que o
        System.out.println("Informe a peça que você deseja movimentar: ");                 // usuário desejar, também podemos notificar o usuário em casos 
        int peca = entrada.nextInt();                                                        // que não é possível de se realizar o movimento escolhido.
        boolean verificador = false;
        int linhaDaPeca = 0;
        int colunaDaPeca = 0;

        for (int lin = 0; lin < tabuleiro.length; lin++) {
            for (int col = 0; col < tabuleiro[0].length; col++) {
                if (tabuleiro[lin][col] == peca) {
                    linhaDaPeca = lin;
                    colunaDaPeca = col;
                }
            }
        }
        if (zerolinha == linhaDaPeca) {                                                       // Esses são os casos em que o verificador é verdadeiro, desse
            if (zerocoluna == colunaDaPeca + 1) {                                             // modo o movimento é verificado como possível de ser realizado         
                verificador = true;                                                           // e, por conseguinte, é executado.
            }
            if (zerocoluna == colunaDaPeca - 1) {
                verificador = true;
            }
        }
        if (zerocoluna == colunaDaPeca) {
            if (zerolinha == linhaDaPeca + 1) {
                verificador = true;
            }
            if (zerolinha == linhaDaPeca - 1) {
                verificador = true;
            }
        }
        if (verificador == false) {                                                            // Em casos que o verificador é encontrado como falso, o código
            System.out.println("Essa peça não é adjacente ao espaço vazio");                 // avisa que não é possível realizar o movimento e pede para o
        } else {                                                                               // usuário escolher outra peça para mover. 
            move++;
            int balde = 0;

            balde = tabuleiro[linhaDaPeca][colunaDaPeca];                                      // Nos casos de sucesso, é acrescentado mais uma unidade ao
            tabuleiro[linhaDaPeca][colunaDaPeca] = tabuleiro[zerolinha][zerocoluna];           // contador de movimento e as peças são movimentadas através de
            tabuleiro[zerolinha][zerocoluna] = balde;                                          // uma variável temporária (balde), essa que é utilizada para
                                                                                               // que nenhum valor seja perdido. 
            balde = linhaDaPeca;
            linhaDaPeca = zerolinha;
            zerolinha = balde;

            balde = colunaDaPeca;
            colunaDaPeca = zerocoluna;
            zerocoluna = balde;

        }
        if (tabuleiro[0][0] != 1 || tabuleiro[0][1] != 2 || tabuleiro[0][2] != 3
                || tabuleiro[1][0] != 4 || tabuleiro[1][1] != 5 || tabuleiro[1][2] != 6        // Nessa parte da função, verificamos se o tabuleiro está
                || tabuleiro[2][0] != 7 || tabuleiro[2][1] != 8 || tabuleiro[2][2] != 0) {     // organizado corretamente (caso de vitória). Caso esteja,
            Jogar(tabuleiro, move, zerolinha, zerocoluna);                                     // imprimimos ele e uma mensagem final parabenizando o usuário
        } else {                                                                               // e informando com quantos movimentos ele finalizou o jogo.
            ImprimeTabuleiro(tabuleiro);
            System.out.println("Parabéns!!! ;D ");
            System.out.println("Você ganhou o jogo com " + move + " movimentos.");
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random gerador = new Random();

        System.out.println("Escolha uma opcao:");
        System.out.println("0 - Se deseja sair do jogo;");
        System.out.println("1 - Se deseja iniciar o jogo;");
        System.out.println("2 - Se deseja ver as instruÃ§Ãµes de como jogar.");

        int opcao = entrada.nextInt();                                                       //Variavel criada para alocar o valor da opcao escolhida pelo usuario.
        int move = 0;

        if (opcao == 0) {                                                                    //Se o valor for 0, o programa irá encerrar logo de inicio.
            System.out.print("Muito obrigado por participar. Jogo encerrado.");
        }

        int dificuldade;
        int zerolinha;
        int zerocoluna;
        int balde;

        while (opcao != 0) {                                                                 //Laco criado para que, se o valor for diferente de zero, o programa rode
                                                                                             //enquanto o usuario quiser.
            switch (opcao) {                                                                 //Switch para rodar a opcao escolhida pelo usuario.
                case 1:                                                                      //Caso referente ao incio do jogo.                                                                            
                    int tabuleiro[][] = {                                                    //Valores iniciais atribuidos a matriz tabuleiro.
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                    };

                    ImprimeTabuleiro(tabuleiro);

                    System.out.println("Escolha entre os seguintes numeros para a opcao de dificuldade desejada:");
                    System.out.println("1 - Facil");
                    System.out.println("2 - Medio");
                    System.out.println("3 - Dificil");
                    dificuldade = entrada.nextInt();                                         //Variavel criada para alocar a dificuldade do jogo escolhida pelo usuario.

                    while (dificuldade < 1 || dificuldade > 3) {                             //Laco criado para possibilitar ao usuario escolher novamente uma opcao que    
                        System.out.println("Dificuldade inexistente. Informe novamente:"); //exista.
                        dificuldade = entrada.nextInt();
                    }

                    if (dificuldade == 1) {                                                  //If destiinado a dificuldade facil.
                        zerolinha = 2;                                                       //Variaveis destinadas a guardar a posicao inicial do zero (espaco).
                        zerocoluna = 2;
                        balde = 0;

                        for (int contador = 1; contador <= 20; contador++) {
                            int m = gerador.nextInt(1, 5);                        //1- para cima; 2- para baixo; 3- para a esquerda; 4- para a direita
                            int resultado[] = new int[2];
                            resultado = Movimento(m, zerolinha, zerocoluna);                 //Chamando funcao Movimento.                                                                               

                            while (resultado[1] > 2 || resultado[1] < 0 || resultado[0] > 2 || resultado[0] < 0) {  //Se os valores que retornarem da funcao Movimento
                                m = gerador.nextInt(1, 5);                                               //ultrapassarem o limite das bordas do tabuleiro,           
                                resultado = Movimento(m, zerolinha, zerocoluna);                                    //o laco ira gerar outro movimento.
                            }

                            balde = tabuleiro[resultado[1]][resultado[0]];                   //A variavel balde guarda o valor da peca aleatoria. Posteriormente esse valor e
                            tabuleiro[resultado[1]][resultado[0]] = 0;                       //substituido por zero (espaco). O valor da posicao do antigo zero e substituido
                            tabuleiro[zerolinha][zerocoluna] = balde;                        //pelo valor da peca, guardado no balde.
                            zerolinha = resultado[1];                                        //O valor de linha e coluna do zero (espaco) e atualizado.
                            zerocoluna = resultado[0];
                        }

                        Jogar(tabuleiro, move, zerolinha, zerocoluna);                       // Invocando a função Jogar para que seja possível organizar o tabuleiro 
                                                                                             // após o embaralhamento.        
                    }

                    if (dificuldade == 2) {
                        zerolinha = 2;
                        zerocoluna = 2;
                        balde = 0;

                        for (int contador = 1; contador <= 40; contador++) {
                            int m = gerador.nextInt(1, 5);                        //1- para cima; 2- para baixo; 3- para a esquerda; 4- para a direita
                            int resultado[] = new int[2];
                            resultado = Movimento(m, zerolinha, zerocoluna);                                                                                 

                            while (resultado[1] > 2 || resultado[1] < 0 || resultado[0] > 2 || resultado[0] < 0) {
                                m = gerador.nextInt(1, 5);
                                resultado = Movimento(m, zerolinha, zerocoluna);
                            }

                            balde = tabuleiro[resultado[1]][resultado[0]];
                            tabuleiro[resultado[1]][resultado[0]] = 0;
                            tabuleiro[zerolinha][zerocoluna] = balde;
                            zerolinha = resultado[1];
                            zerocoluna = resultado[0];
                        }
                        Jogar(tabuleiro, move, zerolinha, zerocoluna);                       // Invocando a função Jogar para que seja possível organizar o tabuleiro
                    }                                                                        // após o embaralhamento.

                    if (dificuldade == 3) {
                        zerolinha = 2;
                        zerocoluna = 2;
                        balde = 0;

                        for (int contador = 1; contador <= 80; contador++) {
                            int m = gerador.nextInt(1, 5);                       //1- para cima; 2- para baixo; 3- para a esquerda; 4- para a direita
                            int resultado[] = new int[2];
                            resultado = Movimento(m, zerolinha, zerocoluna);                                                                                

                            while (resultado[1] > 2 || resultado[1] < 0 || resultado[0] > 2 || resultado[0] < 0) {
                                m = gerador.nextInt(1, 5);
                                resultado = Movimento(m, zerolinha, zerocoluna);
                            }

                            balde = tabuleiro[resultado[1]][resultado[0]];
                            tabuleiro[resultado[1]][resultado[0]] = 0;
                            tabuleiro[zerolinha][zerocoluna] = balde;
                            zerolinha = resultado[1];
                            zerocoluna = resultado[0];
                        }

                        Jogar(tabuleiro, move, zerolinha, zerocoluna);                       // Invocando a função Jogar para que seja possível organizar o tabuleiro
                    }                                                                        // após o embaralhamento.

                    break;

                case 2:                                                                      //Caso referente as intrucoes do jogo.
                    System.out.println("""
                                                                                Este quebra-cabeca consiste em ordenar 8 pecas em um tabuleiro de 3 por 3
                                                           quadrados, movendo sempre os números para o espaco vazio no tabuleiro.""");
                    System.out.println("\nObjetivo do Jogo:");
                    System.out.println("""
                                                           O objetivo do jogo e deixar as pecas em ordem, da esquerda para a direita, de cima a
                                                           baixo, deixando a posicao inferior direita do tabuleiro vazia, ou seja, as 8 pecas devem ser deslizadas no
                                                           tabuleiro, fazendo-se quantos movimentos fossem necessarios, a fim de serem deixadas em uma
                                                           sequencia crescente, ficando apenas o nono quadrado vazio. Mas, muito cuidado: somente as pecas
                                                           adjacentes ao espaco vazio podem ser movidas.""");
                    System.out.println("""
                                                                                A cada rodada o usuario deve escolher qual peca deseja mover, para tal, ele informara o numero da
                                                           peca que deseja.
                                                           """);
                    break;
            }

            System.out.println("Escolha uma opcao:");
            System.out.println("0 - Se deseja sair do jogo;");
            System.out.println("1 - Se deseja iniciar o jogo;");
            System.out.println("2 - Se deseja ver as intrucoes de como jogar.");

            opcao = entrada.nextInt();

            if (opcao == 0) {                                                                //If destinado a exibir uma mensagem de agradecimento ao ter o programa encerrado.
                System.out.print("Muito obrigado por participar. Jogo encerrado.");
            }
        }
    }
}
