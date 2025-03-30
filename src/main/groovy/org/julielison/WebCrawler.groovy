package org.julielison

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import groovyx.net.http.HttpBuilder
import groovyx.net.http.optional.Download
import com.opencsv.CSVWriter

class WebCrawler {
	static final Integer QTD_DESDE_A_PRIMEIRA = 4
	static final PATH = "./Downloads/Aquivos_padrao_TISS/"

	static Document getDocumentForLink(String link) {
		return Jsoup.connect(link).get()
	}

	static String getLinkContainsText(Document doc, String text) {
		return doc.select("a:contains(${text})").first().absUrl("href")
	}

	static List<String> extractHeadersTitle(Elements headersTh) {
		List<String> headersListStr = new ArrayList<>()
		for (int i = 0; i < 3; i++) {
			headersListStr.add(headersTh.get(i).text())
		}
		return headersListStr
	}

	static List<List<String>> extractContentBody(Elements bodyTrs, List<String> headers) {
		List<List<String>> dataMapped = new ArrayList<>()
		dataMapped.add(headers)

		for (int i = 0; i < bodyTrs.size() - QTD_DESDE_A_PRIMEIRA; i++) {
			Elements tds = bodyTrs[i].select("td")
			List<String> lineContent = new ArrayList<>()
			for (int j = 0; j < headers.size(); j++) {
				String value = tds.get(j).text()
				lineContent.add(value)
			}
			dataMapped.add(lineContent)
		}
		return dataMapped
	}

	static void writeInCsv(List<List<String>> dataMapped) {
		String csvFile = PATH + "Histórico_das_Versões(desde_jan-2016).csv"

		new FileWriter(csvFile).with { writer ->
			new CSVWriter(writer).with { csvWriter ->
				dataMapped.each { row ->
					csvWriter.writeNext(row as String[])
				}
				csvWriter.close()
			}
			writer.close()
		}
		println("Dados inseridos com sucesso no arquivo CSV salvo em ")
	}

	static Document runCommonTask() {
		Document docGovAns = getDocumentForLink('https://www.gov.br/ans/pt-br')
		String linkEspacoPrestador = getLinkContainsText(docGovAns, 'Espaço do Prestador')

		Document docPrestadores = getDocumentForLink(linkEspacoPrestador)
		String linkTiss = getLinkContainsText(docPrestadores, 'TISS - Padrão para Troca de Informação de Saúde Suplementar')

		return getDocumentForLink(linkTiss)
	}

	static void runTaskDownload(Document docPrestadores, String linkText1, String linktext2, String fileName) {
		String link = getLinkContainsText(docPrestadores, linkText1)
		Document doc = getDocumentForLink(link)

		String linkFile = getLinkContainsText(doc, linktext2)
		String pathName = PATH + fileName
		File file = new File(pathName)

		HttpBuilder.configure {
			request.uri = linkFile
		}.get {
			Download.toFile(delegate, file)
		}
		println("Arquivo baixado com sucesso e salvo em: " + pathName)
	}

	static void runTask1(Document docPrestadores) {
		runTaskDownload(docPrestadores,
				'Clique aqui para acessar a versão ',
				'Componente de Comunicação.',
				"componente_comunicacao.zip")
	}

	static void runTask2(Document docTiss) {
		String linkTissHistorico = getLinkContainsText(docTiss, 'Clique aqui para acessar todas as versões dos Componentes')
		Document docTissHistorico = getDocumentForLink(linkTissHistorico)

		Element headerTable = docTissHistorico.getElementsByTag('tr')[0]
		Elements headersTh = headerTable.select("th")
		Elements bodyTrs = docTissHistorico.getElementsByTag('tbody')[0].select('tr')

		List<String> headers = extractHeadersTitle(headersTh)
		List<List<String>> dataMapped = extractContentBody(bodyTrs, headers)
		writeInCsv(dataMapped)
	}

	static void runTask3(Document docPrestadores){
		runTaskDownload(docPrestadores,
				'Clique aqui para acessar as planilhas',
				'tabela de erros',
				"tabela_de_erros.xlsx")
	}
}