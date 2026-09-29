package com.ia.ml.weka;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48; // algoritmo de decisão

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

public class DeteccaoDeFraudeBancaria {
	
	private Classifier classificador;
	private Instances dadosTreinamento;
	
	private Attribute atributoValor;
	private Attribute atributoOrigem;
	private Attribute atributoFraude;
	
	public void definirAtributos() { 
        atributoValor = new Attribute("valor");
        
        ArrayList<String> valoresOrigem = new ArrayList<>();
        valoresOrigem.add("internacional");
        valoresOrigem.add("nacional");
        atributoOrigem = new Attribute("Origem", valoresOrigem); 
        
        ArrayList<String> valoresFraude = new ArrayList<>();
        valoresFraude.add("não");
        valoresFraude.add("sim");
        atributoFraude = new Attribute("Fraude", valoresFraude);
        
        ArrayList<Attribute> atributos = new ArrayList<>();
        atributos.add(atributoValor);
        atributos.add(atributoOrigem);
        atributos.add(atributoFraude);
        
        dadosTreinamento = new Instances("transacoes", atributos, 0);
        
        dadosTreinamento.setClassIndex(dadosTreinamento.numAttributes() - 1);
	 }
	
	private void adicionarTransacao(Double valor, String origem, String fraude) {
		Instance instancia = new DenseInstance(dadosTreinamento.numAttributes());
		
		instancia.setDataset(dadosTreinamento);
		
		instancia.setValue(atributoValor, valor);
		instancia.setValue(atributoOrigem, origem);
		instancia.setValue(atributoFraude, fraude);
		
		dadosTreinamento.add(instancia);
	}
	
	public void adicionarExemplos() {
		adicionarTransacao(500.0, "internacional", "sim");
		adicionarTransacao(1000.0, "internacional", "sim");
		adicionarTransacao(7500.0, "internacional", "sim");
		adicionarTransacao(8000.0, "internacional", "sim");
		
		adicionarTransacao(200.0, "nacional", "não");
		adicionarTransacao(150.0, "nacional", "não");
		adicionarTransacao(400.0, "nacional", "não");
		adicionarTransacao(500.0, "nacional", "não");
	}
	
	public void treinarModelo() throws Exception {
		classificador = new J48();
		classificador.buildClassifier(dadosTreinamento);
	}
	
	public String classificarTransacao(double valor, String origem) throws Exception {
		Instance novaInstancia = new DenseInstance(dadosTreinamento.numAttributes());
		novaInstancia.setDataset(dadosTreinamento);
		novaInstancia.setValue(atributoValor, valor);
		novaInstancia.setValue(atributoOrigem, origem);
        novaInstancia.setMissing(atributoFraude); // Boa prática para novas instâncias
		
		// CORREÇÃO: "classifyInstance" ao invés de "classiFyInstance"
		double previsao = classificador.classifyInstance(novaInstancia);
		
		// CORREÇÃO: Sintaxe correta para o cast (int) previsao
		return "Fraude: " + dadosTreinamento.classAttribute().value((int) previsao);
	}

	public static void main (String[] args) {
		Logger.getLogger("com.github.fommil.netlib").setLevel(Level.SEVERE);
		
		DeteccaoDeFraudeBancaria detector = new DeteccaoDeFraudeBancaria();
		
		try {
			
			detector.definirAtributos();
			
			detector.adicionarExemplos();
			
			detector.treinarModelo();
			
			String resultado1 = detector.classificarTransacao(5000.0, "internacional");
			String resultado2 = detector.classificarTransacao(200.0, "nacional");
			String resultado3 = detector.classificarTransacao(40000.0, "internacional");
			String resultado4 = detector.classificarTransacao(80.0, "internacional");
			String resultado5 = detector.classificarTransacao(5000.0, "internacional");
			String resultado6 = detector.classificarTransacao(25000.0, "internacional");
			String resultado7 = detector.classificarTransacao(400000.0, "internacional");
			String resultado8 = detector.classificarTransacao(170268.0, "nacional");
			String resultado9 = detector.classificarTransacao(70.0, "internacional");
			String resultado10 = detector.classificarTransacao(350.0, "internacional");
			String resultado11 = detector.classificarTransacao(5000.0, "nacional");
			
			System.out.println("Teste 1: " + resultado1);
			System.out.println("Teste 2: " + resultado2);
			System.out.println("Teste 3: " + resultado3);
			System.out.println("Teste 4: " + resultado4);
			System.out.println("Teste 5: " + resultado5);
			System.out.println("Teste 6: " + resultado6);
			System.out.println("Teste 7: " + resultado7);
			System.out.println("Teste 8: " + resultado8);
			System.out.println("Teste 9: " + resultado9);
			System.out.println("Teste 10: " + resultado10);
            System.out.println("Teste 11: " + resultado11); // CORREÇÃO: Adicionado o print que faltava
			
		} catch (Exception e) {
            // CORREÇÃO: "classificar" escrito corretamente
			System.err.println("Erro ao classificar a transação: " + e.getMessage());
		}
	}
}