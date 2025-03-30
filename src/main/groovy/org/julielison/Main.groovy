package org.julielison

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


static void main(String[] args) {
	WebCrawler wc = new WebCrawler()

	Document docGovAns = wc.getDocumentForLink('https://www.gov.br/ans/pt-br')
	String linkEspacoPrestador = wc.getLinkContainsText(docGovAns, 'Espaço do Prestador')

	Document docPrestadores = wc.getDocumentForLink(linkEspacoPrestador)
	wc.runTask1(docPrestadores)
	wc.runTask2(docPrestadores)
	wc.runTask3(docPrestadores)
}