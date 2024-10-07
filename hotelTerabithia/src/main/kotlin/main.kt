val hotel = "Mancer"
var nome: String = ""

fun main (){
    println("Bem vindo ao Hotel $hotel")

    println("Qual é o seu nome?")
    nome = readln()

    println("Qual sua senha?")
    var senha = readln()!!.toInt()

    if(senha == 2678){
        inicio()
    }else{
        println("Senha Inválida!\n")
        erroSenha()
    }
}

fun erroSenha(){
    println("Por favor, insira a senha certa!")
    val senha = readln()!!.toInt()

    if(senha == 2678){
        inicio()
    } else erroSenha()
}

fun inicio(){
    println("Seja bem-vindo ao Hotel $hotel, é um imenso praze ter você por aqui.\n")

    println("Selecione uma opção")
    println("1. Reserva de Quartos")
    println("2. Cadastro de Clientes")
    println("3. Pesquisar e Cadastrar Hóspedes")
    println("4. Cadastrar Evento")
    println("5. Abastecer o Carro")
    println("6. Manutenção do Ar Condicionado")

    val opcao = readln()!!.toInt()

    when(opcao){
        1 -> reservaQuarto()
        2 -> cadastroCliente()
        3 -> pesquisarCadastrar()
        4 -> cadastroEvento()
        5 -> abastercerCarro()
        6 -> manutencaoArCondicionado()
        7 -> saindo()
        else ->{
            println("Valor inválido, tente novamente.")
            inicio()
        }
    }
}

fun reservaQuarto(){
    println("RESERVA DE QUARTOS - HOTEL ${hotel.uppercase()}\n")

    print("Qual o valor padrão da diária? ")
    val valorDiaria = readln()!!.toInt()

    if(valorDiaria > 0){
        print("Quantas diárias serão necessárias? ")
        val diarias = readln()!!.toInt()

        if(diarias > 0 && diarias <= 30){
            println("O valor de $diarias dias de hospedagem é de R$ ${valorDiaria * diarias}")

            println("Qual o nome do hóspede? ")
            val hospede = readln()

            println("Qual o quarto para a reserva? ($quartos)")
            val escolhaQuarto = readln()!!.toInt()

            if(escolhaQuarto in 1..totalQuartos){
                if(quartos.contains(escolhaQuarto)) {
                    quartos.remove(escolhaQuarto)
                    println("Quarto $escolhaQuarto confirmado!")
                }else{
                    println("Quarto inválido!")
                }

                println("$nome, você confirma a hospedagem para $hospede por $diarias dias para o quarto $escolhaQuarto por R$$valorDiaria a diária? S/N")

                val confirmacaoDiaria = readln()!!.uppercase()
                when(confirmacaoDiaria){
                    "S" ->{
                        println("$nome, reserva efetuada com sucesso para $hospede")
                        mostrarQuartos()
                    }
                    "N" -> {
                        println("Reserva cancelada com sucesso, você será direcionado para o inicio...")
                        inicio()
                    } else -> {
                        println("Não entendi sua resposta, por favor reinicie a reserva do quarto...")
                        reservaQuarto()
                    }
                }
            }
        }
        else {
            println("Quantida de dias inválido. Reinicie sua reserva...")
            reservaQuarto()
        }
    }else {
        println("Valor inválido\n Por favor insira um valor valido, vamos começar de novo...\n")
        reservaQuarto()
    }
}

val totalQuartos = 20
val quartos = MutableList(totalQuartos) {it + 1}

fun mostrarQuartos(){
    println("Lista de quartos e suas ocupações")
    if(quartos.isEmpty()){
        println("Nenhum quarto disponível.")
    } else {
    for(quarto in quartos){
            println("Quarto $quarto está disponível; ")
        }
    }
    inicio()
}


val listaNomes: MutableList<String> = mutableListOf()
val listaIdade: MutableList<Int> = mutableListOf()

fun cadastroCliente(){

    var meia: Double = 0.0
    var gratuidade = 0
    var totalDiaria: Double = 0.0

    println("CADASTRO DE CLIENTES - HOTEL ${hotel.uppercase()}\n")

    println("Qual o valor padrão da diária?")
    var diaria = readln()!!.toDouble()

    while(true){
        println("Qual o nome do hóspede? ")
        val cadastroHospede = readln()

        if(cadastroHospede.uppercase() == "PARE"){
            break
        }

        println("Qual a idade do hóspede? ")
        val cadastroIdade = readln()!!.toInt()


            when {
                cadastroIdade < 6 -> {
                    gratuidade += 1
                    println("$cadastroHospede possui gratuidade.")
                }

                cadastroIdade > 60 -> {
                    meia += 1
                    totalDiaria = diaria / 2
                    println("$cadastroHospede possui meia.")
                }
                else -> {
                    totalDiaria += diaria
                    println("$cadastroHospede cadastrado(a) com sucesso.")
                }
            }

        listaNomes.add(cadastroHospede)
        listaIdade.add(cadastroIdade)
    }

    val juncaoLista = listaNomes.zip(listaIdade) {str, num -> "$str - $num anos"}
    println("Hóspesdes cadastrados com sucesso: $juncaoLista")

    println("$nome, o valor total das hospedagens é: RS$totalDiaria; $gratuidade gratuidade(s); $meia meia(s).")
}

var listaHospedes: MutableList<String> = mutableListOf("Guilherme Oliveira", "Maria Helena", "Marcos Coelho Filho", "Julio Coielo Santos")
fun pesquisarCadastrar(){
    println("PESQUISA E CADASTRO - HOTEL ${hotel.uppercase()}\n")

    println("O que você gostaria de fazer?")
    println("1. Pesquisar")
    println("2. Cadastrar")
    println("3. Listar")
    println("4. Sair")

    val resp = readln()!!.toInt()
    when(resp){
        1 -> pesquisar(listaHospedes)
        2 -> cadastrar(listaHospedes)
        3 -> listar(listaHospedes)
        4 -> sair()
        else -> erroPesquisarCadastrar()
    }
}

fun pesquisar(listaHospedes: MutableList<String>){
    println("Qual o nome do hóspede?")
    val resp = readln()

    if(listaHospedes.any {it.contains(resp)}){
        println("Hóspede ${listaHospedes.filter {it.contains(resp)}} foi encontrado(a)")
        println("Quer continuar pesquisando? S/N")
        val continuarOne = readln().uppercase()

        when(continuarOne){
            "S" -> {
                pesquisar(listaHospedes)
            }
            "N" -> {
                pesquisarCadastrar()
            }
            else -> {
                erroPesquisarCadastrar()
            }
        }
    }else{
        println("Hóspede não encontrado.")
        println("Gostaria de cadastrar? S/N")
        val continuarTwo = readln().uppercase()

        when(continuarTwo){
            "S" -> {
                listaHospedes.add(resp)
                println("Hóspede $resp cadastrado(a) com sucesso.")
                pesquisarCadastrar()
            }
            "N" -> {
                println("Vamos continuar pesquisando...\n")
                pesquisar(listaHospedes)
            }
            else -> {
                erroPesquisarCadastrar()
            }
        }
    }
}

fun cadastrar(listaHospedes: MutableList<String>){
    println("Qual o nome do hóspede?")
    val resp = readln()
    listaHospedes.add(resp)

    println("Hóspede $resp cadastrado(a) com sucesso.")
    println("lista de hóspedes atualizada:" + listaHospedes + "\n")
    pesquisarCadastrar()
}

fun listar(listaHospedes: MutableList<String>){
    println("Hóspedes cadastrados: " + listaHospedes + "\n")
    pesquisarCadastrar()
}

fun sair(){
    println("Obrigado por usar nosso sistema\nVocê será direcionado para o menu inicial.\n")
    inicio()
}

fun erroPesquisarCadastrar(){
    println("Valor inválido, vamos tentar novamente.\n")
    pesquisarCadastrar()
}
var nConvidados: Int = 0
fun cadastroEvento(){
    val laranja: Int = 150

    println("CADASTRAR EVENTO - ${hotel.uppercase()}\n")

    println("Qual o número de convidados para o evento?")
    nConvidados = readln()!!.toInt()

    if(nConvidados <= 0){
        println("Número de convidados incompatível.\nVamos tentar novamente.")
        cadastroEvento()
    }else if(nConvidados > 350){
        println("Quantidade de convidados superior à capacidade máxima.\nVamos tentar novamente.")
        cadastroEvento()
    }

    if(nConvidados > 220){
        println("Use o auditório Colorado")
        println("Agora vamos ver a agenda do evento.\n")
        auditorioColorado(listaSemana, listaFinalSemanma)
    }else if(nConvidados > 150){
        println("Use o auditório Laranja (inclua mais ${nConvidados - laranja} cadeiras).")
        println("Agora vamos ver a agenda do evento.\n")
        auditorioLaranja(listaSemana, listaFinalSemanma)
    }else if(nConvidados <= 150){
        println("Use o auditório Laranja")
        println("Agora vamos ver a agenda do evento.\n")
        auditorioLaranja(listaSemana, listaFinalSemanma)
    }
}

var listaSemana: MutableList<String> = mutableListOf("Segunda", "Terça", "Quarta", "Quinta", "Sexta")
var listaFinalSemanma: MutableList<String> = mutableListOf("Sábado", "Domingo")
var empresa: String = ""
var diaEvento: String = ""
var horaEvento: Int = 0
fun auditorioLaranja(listaSemana: MutableList<String>, listaFinalSemana: MutableList<String>){
    println("AUDITÓRIO LARANJA - ${hotel.uppercase()}")

    println("Qual o dia do evento?")
        println("Segunda;\nTerça;\nQuarta;\nQuinta;\nSexta;\nSábado;\nDomingo.")
         diaEvento = readln()

    println("Qual a hora do seu evento?")
    horaEvento = readln()!!.toInt()

         if(listaSemana.any {it.contains(diaEvento)} && horaEvento >= 7 && horaEvento < 23){
             println("Qual o nome da empresa?")
                 empresa = readln()
                     println("Auditório reservado para $empresa. ${listaSemana.filter {it.contains(diaEvento)}}, às ${horaEvento}hs.")
                         println("Agora vamos calcular os funcionarios para o evento.\n")
                            calcularFuncionarios()
    }
         else if(listaFinalSemana.any {it.contains(diaEvento)} && horaEvento >= 7 && horaEvento < 15){
             println("Auditório reservado para $empresa. ${listaFinalSemana.filter {it.contains(diaEvento)}}, às ${horaEvento}hs.")
                println("Agora vamos calcular os funcionarios para o evento.\n")
                     calcularFuncionarios()
    }

    if(listaSemana.any {it.contains(diaEvento)} && horaEvento < 7 || horaEvento >= 23){
        println("Auditório indisponível.")
            cadastroEvento()
    }

    if(listaFinalSemana.any {it.contains(diaEvento)} && horaEvento < 7 || horaEvento >= 23){
        println("Auditório indisponível.")
            cadastroEvento()
    }
}

fun auditorioColorado(listaSemana: MutableList<String>, listaFinalSemana: MutableList<String>){
    println("AUDITÓRIO COLORADO - ${hotel.uppercase()}")

        println("Qual o dia do evento?")
            println("Segunda;\nTerça;\nQuarta;\nQuinta;\nSexta\nSábado;\nDomingo.")
                 diaEvento = readln()

    println("Qual a hora do seu evento?")
    horaEvento = readln()!!.toInt()

        if(listaSemana.any {it.contains(diaEvento)} && horaEvento >= 7 && horaEvento < 23){
             println("Qual o nome da empresa?")
                empresa = readln()
                 println("Auditório reservado para $empresa, ${listaSemana.filter {it.contains(diaEvento)}}, às ${horaEvento}hs.")
                    println("Agora vamos calcular os funcionarios para o evento.\n")
                        calcularFuncionarios()
    }
        else if(listaFinalSemana.any {it.contains(diaEvento)} && horaEvento >= 7 && horaEvento < 15){
              println("Auditório reservado para $empresa, ${listaFinalSemana.filter {it.contains(diaEvento)}}, às ${horaEvento}hs.")
                  println("Agora vamos calcular os funcionarios para o evento.\n")
                     calcularFuncionarios()
    }

    if(listaSemana.any {it.contains(diaEvento)} && horaEvento < 7 || horaEvento >= 23){
        println("Auditório indisponível")
            cadastroEvento()
    }

    if(listaFinalSemana.any {it.contains(diaEvento)} && horaEvento < 7 || horaEvento >= 23 ){
        println("Auditório indisponível")
            cadastroEvento()
    }
}
var duracaoEvento = 0
var totalGarcon = 0
var custoTotalGarcon = 0.0
fun calcularFuncionarios(){
    println("CALCULO DE FUNCIONÁRIOS - ${hotel.uppercase()}")

    println("Qual a duração do evento?")
    duracaoEvento = readln()!!.toInt()

    val qGarcon = kotlin.math.ceil(nConvidados / 12.0).toInt()
    val exGarcon = kotlin.math.ceil(duracaoEvento / 2.0).toInt()
    totalGarcon = qGarcon + exGarcon

    var vGarcons = 10.50 * duracaoEvento
    custoTotalGarcon = vGarcons * totalGarcon

    println("São necessários $totalGarcon garçons.")
    println("Custo: R$${String.format("%.2f",custoTotalGarcon)}")
    println("Agora vamos calcular o buffet do ${hotel.uppercase()} para o evento.\n")
    buffet()
}

var valorTotalBuffet = 0.0
fun buffet(){
    println("BUFFET - ${hotel.uppercase()}")

    val qCafe: Double = 0.2
    val qAgua: Double = 0.5
    val qSalgado: Int = 7

    val vCafe: Double = 0.80
    val vAgua: Double = 0.40
    val vSalgado: Int = 34

    val totalCafe = qCafe * nConvidados
    val totalAgua = qAgua * nConvidados
    val totalSalgado = qSalgado * nConvidados

    val valorTotalCafe = vCafe * totalCafe
    val valorTotalAgua = vAgua * totalAgua
    val valorTotalSalgado = vSalgado * totalSalgado

    valorTotalBuffet = valorTotalCafe + valorTotalAgua + valorTotalSalgado

    println("O evento precisará de $totalCafe litros de café, $totalAgua litros de aguá, $totalSalgado salgados.\n")

    if(nConvidados >= 221){
        relatorio("Auditório Colorado")
    }else{
        relatorio("Auditório Laranja")
    }
}

fun relatorio(auditorio: String) {
    println("Evento no Auditório $auditorio")
    println("Nome da empresa: $empresa")
    println("Data: $diaEvento, inicio: ${horaEvento}hs")
    println("Duração: ${duracaoEvento}hs")
    println("Quantidade de Garçons: $totalGarcon")
    println("Quantidade de Convidados: $nConvidados\n")

    println("Custo dos Garçons: R$${String.format("%.2f", custoTotalGarcon)}")
    println("Custo do Buffet: R$${String.format("%.2f", valorTotalBuffet)}")

    val custoTotalFinal = custoTotalGarcon + valorTotalBuffet
    println("Custo Total: R$${String.format("%.2f", custoTotalFinal)}")
    confirmarReserva()
}

fun confirmarReserva(){
    println("Gostaria de efetuar a reserva? S/N")
    val confirmacao = readln().uppercase()

    when(confirmacao){
        "S" -> {
            println("$nome, reserva efetuada com sucesso.\nObrigado(a)!\n")
            inicio()
        }
        "N" -> {
            println("Reserva não efetuada.\nObrigado(a)!\n")
            inicio()
        }
        else -> {
            println("Valor inválido. Por favor responda 'S' ou 'N'")
            confirmarReserva()
        }
    }
}

fun abastercerCarro(){
    println("ABASTECER OS CARROS - ${hotel.uppercase()} ")

    val capacidadeTanque = 42.0
    println("Qual o valor do ácool no posto Wayne Oil?")
        val precoAlcoolWayne = readln()!!.toDouble()
            println("Qual o valor da gasolina no posto Wayne Oil?")
                 val precoGasolinaWayne = readln()!!.toDouble()

    println("Qual o valor do ácool no posto Stark Petrol?")
        var precoAlcoolStark = readln()!!.toDouble()
            println("Qual o valor da gasolina no posto Stark Petrol?")
                val precoGasolinaStark = readln()!!.toDouble()

    val postoWayne = calcularMelhorCusto(precoAlcoolWayne, precoGasolinaWayne)
    val postoStark = calcularMelhorCusto(precoAlcoolStark, precoGasolinaStark)

    val custoWayne = if(postoWayne == "álcool"){
        precoAlcoolWayne * capacidadeTanque
    } else {
        precoGasolinaWayne * capacidadeTanque
    }

    val custoStark = if(postoStark == "álcool"){
        precoAlcoolStark * capacidadeTanque
    } else {
        precoGasolinaStark * capacidadeTanque
    }

    if(custoWayne < custoStark){
        println("$nome, é mais barato abastecer com $escolhaFinal no posto Wayne Oil.\n")
        inicio()
    }else if (custoStark < custoWayne){
        println("$nome, é mais barato abastecer com $escolhaFinal no posto Stark Petrol.\n")
        inicio()
    }else {
        println("Ambos postos estão o mesmo valor.\n")
        inicio()
    }
}

var escolhaFinal: String = ""
fun calcularMelhorCusto(precoAlcool: Double, precoGasolina: Double): String{
    val limiteAlcool = precoGasolina * 0.70
    escolhaFinal = if (precoAlcool <= limiteAlcool){
        "álcool"
    } else {
        "gasolina"
    }
    return escolhaFinal
}

fun manutencaoArCondicionado(){
    println("MANUTENÇÃO DE AR CONDICIONAOD - ${hotel.uppercase()}")

    var nomeDaEmpresa: MutableList<String> = mutableListOf()
    var valorDaEmpresa: MutableList<Double> = mutableListOf()


    while(true) {

        println("Qual o nome da empresa?")
        val nomeEmpresa = readln()
        nomeDaEmpresa.add(nomeEmpresa)

        println("Qual o valor por aparelho")
        val valorAparelho = readln()!!.toDouble()

        println("Qual a quantidade de aparelhos?")
        val quantidadeAparelhos = readln()!!.toInt()

        println("Qual a porcentagem de desconto?")
        val porcentagemDesconto = readln()!!.toInt()

        println("Qual o número mínimo de aparelhos para conseguir o desconto?")
        val quantidadeMinimaDesconto = readln()!!.toInt()

        val valorSemDesconto = valorAparelho * quantidadeAparelhos
        val valorFinal: Double

        if (quantidadeAparelhos >= quantidadeMinimaDesconto) {
            val desconto = (valorSemDesconto * porcentagemDesconto) / 100
            valorFinal = valorSemDesconto - desconto
            println("O serviço da $nomeEmpresa custará R$${String.format("%.2f", valorFinal)}")
        } else {
            valorFinal = valorSemDesconto
            println("O serviço da $nomeEmpresa custará R$${String.format("%.2f", valorSemDesconto)}")
        }

        valorDaEmpresa.add(valorFinal)

        println("Deseja informar novos dados,$nome? (S/N)")
        val novosDados = readln().uppercase()

        if(novosDados == "N"){
            val valorDaEmpresaFormatada = valorDaEmpresa.map { valor -> String.format("%.2f", valor)}
                val junçaoEmpresa = nomeDaEmpresa.zip(valorDaEmpresaFormatada) {empresa, valor -> "$empresa por R$$valor"}.minOrNull()
                    println("O orçamento menor é o $junçaoEmpresa\n")
                        inicio()
                          break
        }
    }
}

fun saindo(){
    println("Saindo...")
}