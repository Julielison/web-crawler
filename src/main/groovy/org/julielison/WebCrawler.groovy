package org.julielison

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import java.lang.annotation.ElementType

//O bot deve acessar o site utilizando os recursos aprendidos no Nível ZG, no caso, a HTTPBuilder;
//Link do site, clique aqui.
//		Acessar o campo "Espaço do Prestador de Serviços de Saúde";
//Acessar o campo "TISS - Padrão para Troca de Informação de Saúde Suplementar";
//Acessar o campo "Padrão TISS Versão Mês/Ano ";
//Realizar o parser no HTML de modo a obter os elementos da tabela de documentos;
//Baixar o arquivo de  Componente de Comunicação, na tabela de arquivos do padrão TISS  e salvar em uma pasta de Downloads no mesmo diretório do projeto (exemplo:  "./Downloads/Aquivos_padrao_TISS");

class WebCrawler {
	Document getDocumentForLink(String link){
		return Jsoup.connect(link).get()
	}

	String getLinkContainsText(Document doc, String text){
		return doc.select("a:contains(${text})").first().absUrl("href")
	}
}
