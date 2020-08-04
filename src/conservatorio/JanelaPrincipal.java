//Eric Clauter - ericclauter@gmail.com [subject: Conservatorio]
package conservatorio;

import static java.lang.Math.pow;
import java.util.Random;
import javax.swing.JOptionPane;

public class JanelaPrincipal extends javax.swing.JFrame {

    String resultadoVar;
    String INICIO, FINAL, tomVar;
    String[] notasVar, codigosVar;
    Integer numeradorVar, qtdeNotasVar, denominadorVar;
    Float batimentosVar;
    String[] motivoVar, escalaVar,parcelaVar;
    float duracaoVar;
    
    String[] codigosFinais, notasFinais;
    String musicaFinal;
    Integer qtdeCompassos;
    Random rand = new Random();
    Float duracaoDaMusica;
    float[] compassos;
    String marcacao;
    String sobre = "Eric Clauter - ericclauter@gmail.com [subject: Conservatorio]\n"
            + "To create random music and music exercises to teach\n"
            + "\n"
            + "Notes:\n"
            + "lá4 = 440Hz = a'\n"
            + "lá5 = a''\n"
            + "lá6 = a'''\n"
            + "\n"
            + "lá3 = a\n"
            + "lá2 = a,\n"
            + "lá1 = a,,\n"
            + "...\n"
            + "\n"
            + "Rhythm (4/4) codes:\n"
            + "a1 = 4 times\n"
            + "a2 = 2 times\n"
            + "a4 = 1 time\n"
            + "a8 = 0.5 time\n"
            + "...\n"
            + "a1. = 4 + 4/2 = 6 times\n"
            + "a1.. = 4 + 4/2 + 4/4 = 7 times\n"
            + "pauses = r\n"
            + "Exemple: a'2. r4 g,,1\n\n";

    public JanelaPrincipal() {
        initComponents();
        
        resultado.setText("");
        
        resultado.setLineWrap(true);

        notas.setText("g'1-c'2-r8");
        numerador.setText("4");
        denominador.setText("4");
        qtdeNotas.setText("25");
        batimentos.setText("88");
        
    }

    public void inicializacao() {
        INICIO = "";
        FINAL = "";

        resultadoVar = INICIO;
    }
    
    public void marcaAntesDaEnenismaNota(int n) {
        for (int i = 0; i < compassos.length-1; i++) {
            Float temporaria2 = compassos[i + 1];
            Float temporaria = compassos[i];
            int tamanhoDoCompasso = temporaria2.intValue() - temporaria.intValue();

            if (n > tamanhoDoCompasso) {
                notasFinais[temporaria.intValue() + tamanhoDoCompasso - 2] = marcacao + notasFinais[temporaria.intValue() + tamanhoDoCompasso - 2];
            }else if (n < 1) {
                notasFinais[temporaria.intValue() + 1 - 2] = marcacao + notasFinais[temporaria.intValue() + 1 - 2];
            }
            else{
                notasFinais[temporaria.intValue() + n - 2] = marcacao + notasFinais[temporaria.intValue() + n - 2];
            }

        }
    }

    public void quebrarCompasso() {
        for (int i = 0; i < compassos.length - 1; i++) {
            Float temporaria2 = compassos[i + 1];
            Float temporaria = compassos[i];
            int tamanhoDoCompasso = temporaria2.intValue() - temporaria.intValue();
            
            int n = tamanhoDoCompasso;

            codigosFinais[temporaria.intValue() + n - 2] = codigosFinais[temporaria.intValue() + n - 2] + "\n";

        }
    }
    public void marcaDepoisDaEnenismaNota(int n) {
        for (int i = 0; i < compassos.length - 1; i++) {
            Float temporaria2 = compassos[i + 1];
            Float temporaria = compassos[i];
            int tamanhoDoCompasso = temporaria2.intValue() - temporaria.intValue();

            if (n > tamanhoDoCompasso) {
                notasFinais[temporaria.intValue() + tamanhoDoCompasso - 2] = notasFinais[temporaria.intValue() + tamanhoDoCompasso - 2] + marcacao;
            } else if (n < 1) {
                notasFinais[temporaria.intValue() + 1 - 2] = notasFinais[temporaria.intValue() + 1 - 2] + marcacao;
            }
            else{
                notasFinais[temporaria.intValue() + n - 2] = notasFinais[temporaria.intValue() + n - 2] + marcacao;
            }

        }
    }
    
    public String separaANotaEmCifras(String nota) {
        String[] temporaria = nota.split("");
        String[] cifra = new String[0];

        for (String temporaria1 : temporaria) {
            if (temporaria1.equals("a") || temporaria1.equals("b") || temporaria1.equals("c") || temporaria1.equals("d") || temporaria1.equals("e") || temporaria1.equals("f") || temporaria1.equals("g") || temporaria1.equals("i") || temporaria1.equals("s") || temporaria1.equals("r")) {
                cifra = aumentaUmString(cifra);
                cifra[cifra.length - 1] = temporaria1;
            }
        }

        String resposta = "";
        for (String cifra1 : cifra) {
            resposta = resposta + cifra1;
        }

        return resposta;
    }

    public String separaANotaEmOitavas(String nota) {
        String[] temporaria = nota.split("");
        String[] oitava = new String[0];

        for (String temporaria1 : temporaria) {
            if (temporaria1.equals("'") || temporaria1.equals(",")) {
                oitava = aumentaUmString(oitava);
                oitava[oitava.length - 1] = temporaria1;
            }
        }

        String resposta = "";

        for (String oitava1 : oitava) {
            resposta = resposta + oitava1;
        }

        return resposta;
    }

    public String separaANotaEmDuracoes(String nota) {
        String[] temporaria = nota.split("");
        String[] duracaoTemporaria = new String[0];

        for (String temporaria1 : temporaria) {
            if (temporaria1.equals("1") || temporaria1.equals("2") || temporaria1.equals("4") || temporaria1.equals("8") || temporaria1.equals("16") || temporaria1.equals("32") || temporaria1.equals("64") || temporaria1.equals(".")) {
                duracaoTemporaria = aumentaUmString(duracaoTemporaria);
                duracaoTemporaria[duracaoTemporaria.length - 1] = temporaria1;
            }
        }

        String resposta = "";
        for (String duracaoTemporaria1 : duracaoTemporaria) {
            resposta = resposta + duracaoTemporaria1;
        }

        return resposta;
    }

    public String extraiCifraOitavada(String nota) {
        String resposta;
        String cifra = separaANotaEmCifras(nota);
        String oitava = separaANotaEmOitavas(nota);

        resposta = cifra + oitava;
        return resposta;
    }

    public int cifraOitavadaParaNumero(String nota) {
        int resposta;
        int multiplicadorOitava;
        String cifra = separaANotaEmCifras(nota);
        String oitava = separaANotaEmOitavas(nota);

        if (oitava.equals("")) {
            multiplicadorOitava = -1;
        } else {

            String[] temporaria = oitava.split("");
            if (temporaria[0].equals("'")) {
                multiplicadorOitava = temporaria.length - 1;
            } else {
                multiplicadorOitava = -(temporaria.length + 1);
            }
        }

        switch (cifra) {
            case "a":
                resposta = 4 + 12 * multiplicadorOitava;
                break;

            case "ais":
                resposta = 5 + 12 * multiplicadorOitava;
                break;
            case "bes":
                resposta = 5 + 12 * multiplicadorOitava;
                break;

            case "b":
                resposta = 6 + 12 * multiplicadorOitava;
                break;

            case "c":
                resposta = -5 + 12 * multiplicadorOitava;
                break;

            case "cis":
                resposta = -4 + 12 * multiplicadorOitava;
                break;
            case "des":
                resposta = -4 + 12 * multiplicadorOitava;
                break;

            case "d":
                resposta = -3 + 12 * multiplicadorOitava;
                break;

            case "dis":
                resposta = -2 + 12 * multiplicadorOitava;
                break;
            case "ees":
                resposta = -2 + 12 * multiplicadorOitava;
                break;

            case "e":
                resposta = -1 + 12 * multiplicadorOitava;
                break;

            case "f":
                resposta = 12 * multiplicadorOitava;
                break;

            case "fis":
                resposta = 1 + 12 * multiplicadorOitava;
                break;
            case "ges":
                resposta = 1 + 12 * multiplicadorOitava;
                break;

            case "g":
                resposta = 2 + 12 * multiplicadorOitava;
                break;

            case "gis":
                resposta = 3 + 12 * multiplicadorOitava;
                break;
            case "aes":
                resposta = 3 + 12 * multiplicadorOitava;
                break;
            
            case "r":
                resposta = 4 + 12 * multiplicadorOitava;
                break;
            default:
                resposta = 4 + 12 * multiplicadorOitava;
                break;
        }

        return resposta;
    }

    public int distanciaEntreDuasCifras(String nota1, String nota2) {
        int distancia;
        int notaInt1, notaInt2;
        
        if(nota1.equals("r") || nota2.equals("r")){
            return 1;
        }
        
        notaInt1 = cifraOitavadaParaNumero(nota1);
        notaInt2 = cifraOitavadaParaNumero(nota2);

        distancia = notaInt2 - notaInt1;

        if (distancia < 0) {
            distancia = -distancia;
        }

        return distancia;
    }

    public void corrigirGeraMusica() {
        String[] notasTemp = musicaFinal.split(" ");
        
        for(int i=0;i<notasTemp.length;i++){//Nao pode haver intervalos maiores que quintas
            
            if(i!=0){

                while(distanciaEntreDuasCifras(extraiCifraOitavada(notasTemp[i]),extraiCifraOitavada(notasTemp[i-1]))>=7){
                    notasTemp[i] = extraiCifraOitavada(escalaVar[rand.nextInt(escalaVar.length)]) + separaANotaEmDuracoes(notasTemp[i]);
                }
                
            }
        }
        musicaFinal = "";
        for (String notasTemp1 : notasTemp) {
            musicaFinal = musicaFinal + notasTemp1 + " ";
        }
        
        
    }

    public float codigoParaTempo(String codigo) {
        float duracaoFinal;// = (float) denominadorVar / Float.parseFloat(codigo);

        switch (codigo) {
            case "1.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2");
                break;
            case "1..":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4");
                break;
            case "1...":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8");
                break;
            case "1....":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16");
                break;
            case "1.....":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32");
                break;
            case "1......":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("1") + denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            case "2.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4");
                break;
            case "2..":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8");
                break;
            case "2...":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16");
                break;
            case "2....":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32");
                break;
            case "2.....":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("2") + denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            case "4.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8");
                break;
            case "4..":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16");
                break;
            case "4...":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32");
                break;
            case "4....":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("4") + denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            case "8.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16");
                break;
            case "8..":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32");
                break;
            case "8...":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("8") + denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            case "16.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32");
                break;
            case "16..":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("16") + denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            case "32.":
                duracaoFinal = (float) denominadorVar / Float.parseFloat("32") + denominadorVar / Float.parseFloat("64");
                break;
            default:
                duracaoFinal = (float) denominadorVar / Float.parseFloat(codigo);
                break;
        }

        return duracaoFinal;
    }

    public String tempoParaCodigo(float duracao) {
        Double temporaria;

        duracao = denominadorVar / duracao;
        String codigo = "" + (int) duracao;
        duracao = denominadorVar / duracao;

        if (duracao % 0.046875 == 0) {//um ponto
            for (int i = 0; i < 11 + 1; i++) {
                temporaria = 0.046875 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 32 / pow(2, i));
                    codigo = "" + temporaria.intValue() + ".";
                    return codigo;
                }
            }
        }
        if (duracao % 0.109375 == 0) {//dois pontos
            for (int i = 0; i < 10 + 1; i++) {
                temporaria = 0.109375 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 16 / pow(2, i));
                    codigo = "" + temporaria.intValue() + "..";
                    return codigo;
                }
            }
        }
        if (duracao % 0.234375 == 0) {//três pontos
            for (int i = 0; i < 9 + 1; i++) {
                temporaria = 0.234375 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 8 / pow(2, i));
                    codigo = "" + temporaria.intValue() + "...";
                    return codigo;
                }
            }
        }
        if (duracao % 0.484375 == 0) {//quatro pontos
            for (int i = 0; i < 8 + 1; i++) {
                temporaria = 0.484375 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 4 / pow(2, i));
                    codigo = "" + temporaria.intValue() + "....";
                    return codigo;
                }
            }
        }
        if (duracao % 0.984375 == 0) {//cinco pontos
            for (int i = 0; i < 7 + 1; i++) {
                temporaria = 0.984375 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 2 / pow(2, i));
                    codigo = "" + temporaria.intValue() + ".....";
                    return codigo;
                }
            }
        }
        if (duracao % 1.984375 == 0) {//seis pontos
            for (int i = 0; i < 6 + 1; i++) {
                temporaria = 1.984375 * pow(2, i);
                if (duracao == temporaria.floatValue()) {
                    temporaria = (double) (denominadorVar * 1 / pow(2, i));
                    codigo = "" + temporaria.intValue() + "......";
                    return codigo;
                }
            }
        }

        return codigo;
    }

    public float[] aumentaUmFloat(float[] vetor) {
        float[] temporaria = new float[vetor.length];

        System.arraycopy(vetor, 0, temporaria, 0, vetor.length);

        vetor = new float[temporaria.length + 1];

        System.arraycopy(temporaria, 0, vetor, 0, temporaria.length);

        return vetor;
    }

    public String[] aumentaUmString(String[] vetor) {
        String[] temporaria = new String[vetor.length];

        System.arraycopy(vetor, 0, temporaria, 0, vetor.length);

        vetor = new String[temporaria.length + 1];

        System.arraycopy(temporaria, 0, vetor, 0, temporaria.length);

        return vetor;
    }

    public void criarListaCodigos() {
        codigosFinais = new String[0];
        qtdeCompassos = 0;

        compassos = new float[1];
        compassos[0] = 1.0f;

        for (int i = 0; codigosFinais.length < qtdeNotasVar; i++) {

            String[] temporaria = codigosDeUmCompasso();
            for (String temporaria1 : temporaria) {
                codigosFinais = aumentaUmString(codigosFinais);
            }

            System.arraycopy(temporaria, 0, codigosFinais, i, temporaria.length);
            
            qtdeCompassos++;

            compassos = aumentaUmFloat(compassos);
            compassos[qtdeCompassos] = codigosFinais.length + 1;

            i = i + temporaria.length - 1;
        }

        qtdeNotasVar = codigosFinais.length;
    }

    public String[] codigosDeUmCompasso() {
        float[] resultadoFloat = new float[1];

        int erro = 0;//evita que fique procurando combinações quando não há possíveis

        for (int i = 0; somaTempos(resultadoFloat) < numeradorVar; i++) {

            resultadoFloat[i] = codigoParaTempo(codigosVar[rand.nextInt(codigosVar.length)]);
            resultadoFloat = aumentaUmFloat(resultadoFloat);

            if (somaTempos(resultadoFloat) > numeradorVar) {
                i = -1;
                resultadoFloat = new float[1];

                erro++;
                if (erro >= 100) {//falhou 100 vezes em achar um compasso compatível
                    JOptionPane.showMessageDialog(this, "Não há combinação desses valores que gere compassos inteiros!\nEntao foram gerados compassos simples", "", 0);

                    qtdeNotasVar = 1;

                    String[] aProvaDeFalhas = new String[numeradorVar];
                    for (int j = 0; j < aProvaDeFalhas.length; j++) {
                        aProvaDeFalhas[j] = "" + denominadorVar;
                    }

                    return aProvaDeFalhas;
                }

            }

        }

        String[] resultadoString = new String[resultadoFloat.length - 1];
        for (int i = 0; i < resultadoFloat.length - 1; i++) {
            resultadoString[i] = tempoParaCodigo(resultadoFloat[i]);
        }
        return resultadoString;
    }

    public void criarListaNotas() {
        notasFinais = new String[qtdeNotasVar];

        for (int i = 0; i < qtdeNotasVar; i++) {

            notasFinais[i] = notasVar[rand.nextInt(notasVar.length)];

            if (i != 0) {

                if (distanciaEntreDuasCifras(notasFinais[i], notasFinais[i - 1]) >= 7) {
                    i--;
                }

            }

        }

    }

    public float somaTempos(float[] vetor) {
        float soma = 0.0f;

        for (int i = 0; i < vetor.length; i++) {
            soma = soma + vetor[i];
        }

        return soma;
    }

    public void inversao() {
        String[] temporaria = new String[motivoVar.length];

        for (int i = 0; i < motivoVar.length; i++) {
            temporaria[i] = motivoVar[motivoVar.length - 1 - i];
        }

        System.arraycopy(temporaria, 0, motivoVar, 0, temporaria.length);

        for (String temporaria1 : temporaria) {
            musicaFinal = musicaFinal + temporaria1 + " ";
        }

        qtdeCompassos++;
    }

    public void modificado() {
        String[] temporaria = new String[motivoVar.length];
        System.arraycopy(motivoVar, 0, temporaria, 0, motivoVar.length);

        int modificarQuanto = rand.nextInt(6) + 1;
        int qualNotaASerAlterada = rand.nextInt(temporaria.length);

        for (int j = 0; j < escalaVar.length; j++) {

            if (extraiCifraOitavada(temporaria[qualNotaASerAlterada]).equals(escalaVar[j])) {
                String cifra = escalaVar[(j + modificarQuanto) % 7];
                temporaria[qualNotaASerAlterada] = cifra + separaANotaEmDuracoes(temporaria[qualNotaASerAlterada]);
            }
        }

        for (String temporaria1 : temporaria) {
            musicaFinal = musicaFinal + temporaria1 + " ";
        }

        qtdeCompassos++;
    }

    public void transposicao() {
        String[] temporaria = new String[motivoVar.length];
        System.arraycopy(motivoVar, 0, temporaria, 0, motivoVar.length);

        int transporQuanto = rand.nextInt(6) + 1;
        

        for (int i = 0; i < temporaria.length; i++) {
            String substituta = temporaria[i];
            for (int j = 0; j < escalaVar.length; j++) {

                if (extraiCifraOitavada(temporaria[i]).equals(escalaVar[j])) {
                    
                    String cifra = escalaVar[(j + transporQuanto) % escalaVar.length];
                    
                    substituta = cifra + separaANotaEmDuracoes(temporaria[i]);
                    
                }
            }
            temporaria[i] = substituta;
        }

        for (String temporaria1 : temporaria) {
            musicaFinal = musicaFinal + temporaria1 + " ";
        }

        qtdeCompassos++;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resultado = new javax.swing.JTextArea();
        gerar = new javax.swing.JButton();
        numerador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        qtdeNotas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        notas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        denominador = new javax.swing.JTextField();
        informacoes = new javax.swing.JLabel();
        batimentos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Sobre = new javax.swing.JButton();
        marcacaoAntes = new javax.swing.JTextField();
        marcacaoDepois = new javax.swing.JTextField();
        marcarQualNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultado.setColumns(20);
        resultado.setRows(5);
        jScrollPane1.setViewportView(resultado);

        gerar.setText("Gerar");
        gerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarActionPerformed(evt);
            }
        });

        jLabel1.setText("Compasso:");

        jLabel2.setText("Qtde Aproximada de Notas: ");

        jLabel4.setText("Notas Disponíveis (separe por hífens): ");

        jLabel6.setText("/");

        informacoes.setText("Informações");

        jLabel5.setText("Batimentos/Minuto: ");

        Sobre.setText("Sobre");
        Sobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobreActionPerformed(evt);
            }
        });

        jLabel3.setText("Marcação anterior:");

        jLabel7.setText("Marcar a nota:");

        jLabel8.setText("Marcação posterior:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(marcacaoAntes, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(marcacaoDepois)
                            .addComponent(marcarQualNota)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(numerador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(denominador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(batimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(notas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2)
                            .addComponent(qtdeNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Sobre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gerar)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcacaoAntes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcarQualNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcacaoDepois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(informacoes)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtdeNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(denominador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gerar)
                    .addComponent(Sobre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarActionPerformed
        parcelaVar = notas.getText().split("-");
        
        notasVar = new String[parcelaVar.length];
        codigosVar = new String[parcelaVar.length];
        
        for(int i=0;i<parcelaVar.length;i++){
            notasVar[i]=extraiCifraOitavada(parcelaVar[i]);
            codigosVar[i]=separaANotaEmDuracoes(parcelaVar[i]);
        }

        numeradorVar = Integer.parseInt(numerador.getText());
        denominadorVar = Integer.parseInt(denominador.getText());

        qtdeNotasVar = Integer.parseInt(qtdeNotas.getText());
        batimentosVar = Float.parseFloat(batimentos.getText());
      
        criarListaCodigos();
        criarListaNotas(); //respeita intervalo menor que 5a (problema com as pausas "r")

        inicializacao();
        
        int qualANotaPraMarcar = 0;
        try{
            qualANotaPraMarcar = Integer.parseInt(marcarQualNota.getText());
        }
        catch(NumberFormatException temp){
            qualANotaPraMarcar = 0;
        }

        marcacao = marcacaoAntes.getText();
        marcaAntesDaEnenismaNota(qualANotaPraMarcar);
        marcacao = marcacaoDepois.getText();
        marcaDepoisDaEnenismaNota(qualANotaPraMarcar);
        
        quebrarCompasso();
        
        for (int j = 0; j < qtdeNotasVar; j++) {
            //resultadoVar = resultadoVar+notasVar[rand.nextInt(notasVar.length)];
            resultadoVar = resultadoVar + notasFinais[j];

            //resultadoVar = resultadoVar+codigosVar[rand.nextInt(codigosVar.length)]+" ";
            resultadoVar = resultadoVar + codigosFinais[j] + " ";
        }

        duracaoDaMusica = (float) qtdeCompassos * numeradorVar * 60 / batimentosVar;
        
        informacoes.setText("Compassos: " + qtdeCompassos + ".  Notas: " + qtdeNotasVar + ".  Duração (seg): " + duracaoDaMusica.shortValue());
        
        resultadoVar = resultadoVar + FINAL;
        resultado.setText(resultadoVar);
        
        resultado.selectAll();
        resultado.copy();
    }//GEN-LAST:event_gerarActionPerformed

    private void SobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobreActionPerformed
        JOptionPane.showMessageDialog(null, sobre, "", 1);
    }//GEN-LAST:event_SobreActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JanelaPrincipal().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sobre;
    private javax.swing.JTextField batimentos;
    private javax.swing.JTextField denominador;
    private javax.swing.JButton gerar;
    private javax.swing.JLabel informacoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marcacaoAntes;
    private javax.swing.JTextField marcacaoDepois;
    private javax.swing.JTextField marcarQualNota;
    private javax.swing.JTextField notas;
    private javax.swing.JTextField numerador;
    private javax.swing.JTextField qtdeNotas;
    private javax.swing.JTextArea resultado;
    // End of variables declaration//GEN-END:variables
}
