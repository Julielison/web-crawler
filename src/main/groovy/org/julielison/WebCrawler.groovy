package org.julielison

import com.opencsv.CSVWriter
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class WebCrawler {
	static final Integer QTD_DESDE_A_PRIMEIRA = 4

	static Document getDocumentForLink(String link){
		return Jsoup.connect(link).get()
	}

	static String getLinkContainsText(Document doc, String text){
		return doc.select("a:contains(${text})").first().absUrl("href")
	}

	static List<String> extractHeadersTitle(Elements headersTh){
		List<String> headersListStr = new ArrayList<>()
		for (int i = 0; i < 3; i++){
			headersListStr.add(headersTh.get(i).text())
		}
		return headersListStr
	}

	static List<List<String>> extractContentBody(Elements bodyTrs, List<String> headers){
		List<List<String>> dataMapped = new ArrayList<>()
		dataMapped.add(headers)

		for (int i = 0; i < bodyTrs.size() - QTD_DESDE_A_PRIMEIRA; i++){
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

	static void writeInCsv(List<List<String>> dataMapped){
		String csvFile = "./Histórico das versões dos Componentes do Padrão TISS (desde jan-2016).csv"

		new FileWriter(csvFile).with { writer ->
			new CSVWriter(writer).with { csvWriter ->
				dataMapped.each { row ->
					csvWriter.writeNext(row as String[])
				}
				csvWriter.close()
			}
			writer.close()
		}
		println("Dados inseridos com sucesso no arquivo CSV.")
	}

	void runTask1(Document docPrestadores){

	}

	static void runTask2(Document docPrestadores) {
		String linkTiss = getLinkContainsText(docPrestadores, 'TISS - Padrão para Troca de Informação de Saúde Suplementar')

		Document docTiss = getDocumentForLink(linkTiss)
		String linkTissHistorico = getLinkContainsText(docTiss, 'Clique aqui para acessar todas as versões dos Componentes')

		Document docTissHstorico = getDocumentForLink(linkTissHistorico)
		Element headerTable = docTissHstorico.getElementsByTag('tr')[0]
		Elements headersTh = headerTable.select("th")
		Elements bodyTrs = docTissHstorico.getElementsByTag('tbody')[0].select('tr')

		List<String> headers = extractHeadersTitle(headersTh)
		List<List<String>> dataMapped = extractContentBody(bodyTrs, headers)
		writeInCsv(dataMapped)
	}

	void runTask3(Document docPrestadores){

	}
}
