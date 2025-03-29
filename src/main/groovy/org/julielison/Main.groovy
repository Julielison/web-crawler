package org.julielison

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


static void main(String[] args) {
	WebCrawler wc = new WebCrawler()

	Document docGovAns = wc.getDocumentForLink('https://www.gov.br/ans/pt-br')
	String linkEspacoPrestador = wc.getLinkContainsText(docGovAns, 'Espaço do Prestador')

	Document docPrestadores = wc.getDocumentForLink(linkEspacoPrestador)
	String linkTiss = wc.getLinkContainsText(docPrestadores, 'TISS - Padrão para Troca de Informação de Saúde Suplementar')

	Document docTiss = wc.getDocumentForLink(linkTiss)
	String linkTissHistorico = wc.getLinkContainsText(docTiss, 'Clique aqui para acessar todas as versões dos Componentes')

	Document docTissHstorico = wc.getDocumentForLink(linkTissHistorico)
	println(docTissHstorico)
}