<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
    <title>Datenschutz</title>
    <link rel="stylesheet" href="css/style.css">
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	<div class="bigWrapper, container" style="letter-spacing: .1em">
        <div class="headerContainerImage">
            <div class="headerContainerContent">
                <h1 class="text-center">Unsere Datenschutzerkl&aumlrung</h1><br>
                <h5 class="text-center">gem&auml&szlig der Vorgaben der Datenschutz-Grundverordnung (EU)</h5><br><br>
            </div>
        </div>

        <!-- Allgemein Container -->
        <div class="informationContainer">
            <div class="informationContainerChild">
                <h3 class="informationContainerTitle underline">Allgemein</h3>
            </div>
            <div class="informationContainerChild">
                <p>Wir haben diese Datenschutzerkl&aumlrung (Fassung 01.07.2020-311190782) verfasst, um Ihnen gem&auml&szlig der Vorgaben der <a class="adsimple-311190782" href="https://eur-lex.europa.eu/legal-content/DE/ALL/?uri=celex%3A32016R0679&amp;tid=311190782" target="_blank" rel="noopener">Datenschutz-Grundverordnung (EU) 2016/679</a> zu erkl&aumlren, welche Informationen wir sammeln, wie wir Daten verwenden und welche Entscheidungsm&oumlglichkeiten Sie als Besucher dieser Webseite haben.</p>
                <p>Leider liegt es in der Natur der Sache, dass diese Erkl&aumlrungen sehr technisch klingen, wir haben uns bei der Erstellung jedoch bem&uumlht die wichtigsten Dinge so einfach und klar wie m&oumlglich zu beschreiben.</p>
            </div>
        </div>
        
        <!-- Datenspeicherung Container -->
        <div class="informationContainer">
            <div class="informationContainerChild">
                <h3 class="informationContainerTitle underline">Datenspeicherung</h3>
            </div>
            <div class="informationContainerChild">
                <p>Wenn Sie heutzutage Webseiten besuchen, werden gewisse Informationen automatisch erstellt und gespeichert, so auch auf dieser Webseite.</p>
                <p>Wenn Sie unsere Webseite so wie jetzt gerade besuchen, speichert unser Webserver (Computer auf dem diese Webseite gespeichert ist) automatisch Daten wie</p>
                <ul class="adsimple-311190782">
                <li class="adsimple-311190782">die Adresse (URL) der aufgerufenen Webseite</li>
                <li class="adsimple-311190782">Browser und Browserversion</li>
                <li class="adsimple-311190782">das verwendete Betriebssystem</li>
                <li class="adsimple-311190782">die Adresse (URL) der zuvor besuchten Seite (Referrer URL)</li>
                <li class="adsimple-311190782">den Hostname und die IP-Adresse des Ger&aumlts von welchem aus zugegriffen wird</li>
                <li class="adsimple-311190782">Datum und Uhrzeit</li>
                </ul>
                <p>in Dateien (Webserver-Logfiles).</p>
            </div>
        </div>

        <!-- Rechte Container -->
        <div class="informationContainer">
            <div class="informationContainerChild">
                <h3 class="informationContainerTitle underline">Rechte laut DSGVO</h3>
            </div>
            <div class="informationContainerChild">
                <p>Ihnen stehen laut den Bestimmungen der DSGVO grunds&aumltzlich die folgende Rechte zu:</p>
                <ul class="adsimple-311190782">
                <li class="adsimple-311190782">Recht auf Berichtigung (Artikel 16 DSGVO)</li>
                <li class="adsimple-311190782">Recht auf L&oumlschung („Recht auf Vergessenwerden“) (Artikel 17 DSGVO)</li>
                <li class="adsimple-311190782">Recht auf Einschr&aumlnkung der Verarbeitung (Artikel 18 DSGVO)</li>
                <li class="adsimple-311190782">Recht auf Benachrichtigung – Mitteilungspflicht im Zusammenhang mit der Berichtigung oder L&oumlschung personenbezogener Daten oder der Einschr&aumlnkung der Verarbeitung (Artikel 19 DSGVO)</li>
                <li class="adsimple-311190782">Recht auf Daten&uumlbertragbarkeit (Artikel 20 DSGVO)</li>
                <li class="adsimple-311190782">Widerspruchsrecht (Artikel 21 DSGVO)</li>
                <li class="adsimple-311190782">Recht, nicht einer ausschlie&szliglich auf einer automatisierten Verarbeitung — einschlie&szliglich Profiling — beruhenden Entscheidung unterworfen zu werden (Artikel 22 DSGVO)</li>
                </ul>
                <p>Wenn Sie glauben, dass die Verarbeitung Ihrer Daten gegen das Datenschutzrecht verst&ouml&szligt oder Ihre datenschutzrechtlichen Anspr&uumlche sonst in einer Weise verletzt worden sind, k&oumlnnen Sie sich an die <a class="adsimple-311190782" href="https://www.bfdi.bund.de" target="_blank" rel="noopener">Bundesbeauftragte f&uumlr den Datenschutz und die Informationsfreiheit (BfDI)</a> wenden.</p>
            </div>
        </div>
    </div>
    <%=HtmlDefaults.generateHtmlFooter()%>
</body>

</html>