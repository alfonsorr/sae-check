import $ivy.`com.lihaoyi::requests:0.7.0`
import $ivy.`org.jsoup:jsoup:1.14.3`

import org.jsoup.Jsoup
import scala.jdk.CollectionConverters._

val url = "https://www.mapa.gob.es/es/ganaderia/temas/comercio-exterior-ganadero/export/sistema_autocontroles.aspx"

val r = requests.get(url)
val doc = Jsoup.parse(r.data.toString)
val urlFile = doc.select("a").asScala.map(_.attr("href")).filter(_.contains("clasificacioncertificadospr")).head
val file = os.pwd / "executions.txt"
val lastUrlFile = os.read.lines(file).last
val returnString = if (lastUrlFile != urlFile) {
  os.write.append(file, urlFile)
  urlFile
}
else ""
println(returnString)
