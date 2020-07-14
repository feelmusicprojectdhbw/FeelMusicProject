package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;
import main.obj.Feelings;
import main.obj.Genres;
import main.obj.Languages;
import main.obj.Styles;

/**
 * Servlet implementation class FeelMusic_Servlet
 */
@WebServlet("/FeelMusic")
public class FeelMusic_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Database db;
	private static FeelMusic_Servlet me;

	public FeelMusic_Servlet() {
		super();
		FeelMusic_Servlet.me = this;
		if (db == null) {
			try {
				db = Database.getDatabase();
				if(!db.isDumped()) {
					dump(db);
				}
				Genres.requery(db);
				Languages.reloadLanguages(db);
				Feelings.reloadFeelings(db);
				Styles.reloadStyles(db);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Ist bereits erstellt");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("landingpage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static FeelMusic_Servlet getMe() {
		return me;
	}

	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

	private void dump(Database db2)
			throws IllegalAccessException, ClassNotFoundException, InstantiationException, SQLException {

		try {
			db.getConnection().createStatement().execute("DROP TABLE albums");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE albums (\r\n"
						+ "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  name varchar(50) NOT NULL DEFAULT '0',\r\n" + "  releasedate int DEFAULT NULL,\r\n"
						+ "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM albums");
		db.getConnection().createStatement()
				.execute("INSERT INTO albums (name, releasedate) VALUES\r\n" + "	('Single', 0)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  artists");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE artists (\r\n"
						+ "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  name varchar(50) DEFAULT NULL,\r\n" + "  link varchar(100) DEFAULT NULL,\r\n"
						+ "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM artists");
		db.getConnection().createStatement()
				.execute("INSERT INTO artists (name, link) VALUES\r\n" + "		('-', NULL),\r\n" + 
						"	('Wildstylez', 'https://de.wikipedia.org/wiki/Wildstylez'),\r\n" + 
						"	('Sound Rush', 'https://hardstyle.fandom.com/de/wiki/Sound_Rush'),\r\n" + 
						"	('Ruby Prophet', 'https://www.facebook.com/RubyProphetOfficial/'),\r\n" + 
						"	('Sub Zero Project', 'https://de.wikipedia.org/wiki/Sub_Zero_Project'),\r\n" + 
						"	('Dr. Peacock', 'https://de.wikipedia.org/wiki/Dr._Peacock'),\r\n" + 
						"	('Audiotricz', 'https://hardstyle.fandom.com/de/wiki/Audiotricz'),\r\n" + 
						"	('TNT', 'https://hardstyle.fandom.com/de/wiki/TNT'),\r\n" + 
						"	('Wasted Penguinz', 'https://en.wikipedia.org/wiki/Wasted_Penguinz'),\r\n" + 
						"	('Headhunterz', 'https://de.wikipedia.org/wiki/Headhunterz'),\r\n" + 
						"	('Project One', 'https://de.wikipedia.org/wiki/Project_One_(Musikprojekt)'),\r\n" + 
						"	('Noisecontrollers', 'https://de.wikipedia.org/wiki/Noisecontrollers'),\r\n" + 
						"	('KiFi', 'https://en.wikipedia.org/wiki/Ki_Fitzgerald'),\r\n" + 
						"	('Eurielle', 'http://eurielle.com/'),\r\n" + 
						"	('Atmozfears', 'https://de.wikipedia.org/wiki/Atmozfears'),\r\n" + 
						"	('Dame', 'https://de.wikipedia.org/wiki/Dame_(Rapper)'),\r\n" + 
						"	('Sasha F', 'https://www.facebook.com/djsashaf'),\r\n" + 
						"	('Rooler', 'https://www.facebook.com/Roolerofficial'),\r\n" + 
						"	('Code Black', 'https://en.wikipedia.org/wiki/Code_Black_(DJ)'),\r\n" + 
						"	('Syren', 'https://www.facebook.com/SyrenDJ/'),\r\n" + 
						"	('Mekanikal', 'https://www.facebook.com/MekanikalHardstyle/'),\r\n" + 
						"	('Kronos', 'https://www.facebook.com/officialdjkronos'),\r\n" + 
						"	('Toneshifterz', 'https://www.toneshifterz.com/'),\r\n" + 
						"	('Activator', 'https://www.facebook.com/activatordj'),\r\n" + 
						"	('E-Force', 'https://www.facebook.com/eforcenl'),\r\n" + 
						"	('Warface', 'https://www.facebook.com/WarfaceOfficial/'),\r\n" + 
						"	('Zatox', 'https://hardstyle.fandom.com/wiki/Zatox'),\r\n" + 
						"	('Frontliner', 'https://hardstyle.fandom.com/wiki/Frontliner'),\r\n" + 
						"	('Crystal Lake', 'https://edm.fandom.com/wiki/Crystal_Lake'),\r\n" + 
						"	('Outer Mind', 'https://www.beatport.com/artist/outer-mind/158378'),\r\n" + 
						"	('Dark Base', 'null'),\r\n" + 
						"	('Brenan Heart', 'https://en.wikipedia.org/wiki/Brennan_Heart'),\r\n" + 
						"	('Villain', 'https://hardstyle.fandom.com/de/wiki/MC_Villain'),\r\n" + 
						"	('Refuzion', 'https://hardstyle.fandom.com/de/wiki/Refuzion'),\r\n" + 
						"	('Hardwell', 'https://en.wikipedia.org/wiki/Hardwell'),\r\n" + 
						"	('Da Tweekaz', 'http://www.datweekaz.com/'),\r\n" + 
						"	('Rob & Chris', 'https://www.robundchris.de/'),\r\n" + 
						"	('Broiler', 'https://en.wikipedia.org/wiki/Broiler_(music_producers)'),\r\n" + 
						"	('Serzo', 'https://www.facebook.com/serzomusic/'),\r\n" + 
						"	('Cyber', 'https://hardstyle.fandom.com/de/wiki/Cyber'),\r\n" + 
						"	('Adrenalize', 'https://hardstyle.fandom.com/de/wiki/Adrenalize'),\r\n" + 
						"	('Davide Sonar', 'https://hardstyle.fandom.com/de/wiki/Davide_Sonar'),\r\n" + 
						"	('MickeyG', 'https://www.facebook.com/mickeygmusic/'),\r\n" + 
						"	('Sephyx', 'https://hardstyle.fandom.com/de/wiki/Sephyx'),\r\n" + 
						"	('Ferry Corsten', 'https://en.wikipedia.org/wiki/Ferry_Corsten'),\r\n" + 
						"	('Gouryella', 'https://en.wikipedia.org/wiki/Gouryella'),\r\n" + 
						"	('Tiësto', 'https://en.wikipedia.org/wiki/Ti%C3%ABsto'),\r\n" + 
						"	('D-Block & S-te-Fan', 'https://www.dblock-stefan.com/'),\r\n" + 
						"	('Noiseshock', 'https://www.discogs.com/de/artist/2046727-Noiseshock'),\r\n" + 
						"	('Avi8', 'https://hardstyle.fandom.com/de/wiki/Avi8'),\r\n" + 
						"	('In Phase', 'null'),\r\n" + 
						"	('Dypression', 'https://hardstyle.fandom.com/de/wiki/Dypression'),\r\n" + 
						"	('Eiffel 65', 'https://en.wikipedia.org/wiki/Eiffel_65'),\r\n" + 
						"	('Green Day', 'https://greenday.com/'),\r\n" + 
						"	('Omyqron', 'https://www.facebook.com/Omyqron/'),\r\n" + 
						"	('Futago', 'https://www.youtube.com/channel/UCFtImZlT_WcT_KK17q7jFKA'),\r\n" + 
						"	('Evil Activities', 'https://en.wikipedia.org/wiki/Evil_Activities'),\r\n" + 
						"	('Endymion', 'https://hardstyle.fandom.com/de/wiki/Endymion'),\r\n" + 
						"	('E-Life', 'https://en.wikipedia.org/wiki/E-Life'),\r\n" + 
						"	('Martin Garrix', 'https://en.wikipedia.org/wiki/Martin_Garrix'),\r\n" + 
						"	('Justin Mylo', 'https://en.wikipedia.org/wiki/Justin_Mylo'),\r\n" + 
						"	('Rebind', 'https://www.facebook.com/rebindmusic'),\r\n" + 
						"	('Lacuna', 'https://www.discogs.com/de/artist/97524-Lacuna'),\r\n" + 
						"	('Mindblast', 'https://www.facebook.com/mindblastmusic/'),\r\n" + 
						"	('Alari', 'https://www.facebook.com/alarimusic/'),\r\n" + 
						"	('Michael Jo', 'https://www.discogs.com/artist/5177713-Michael-Jo'),\r\n" + 
						"	('Darren Styles', 'https://en.wikipedia.org/wiki/Darren_Styles'),\r\n" + 
						"	('Coone', 'https://www.djcoone.com/'),\r\n" + 
						"	('Destiny', 'null'),\r\n" + 
						"	('Matthew Koma', 'https://en.wikipedia.org/wiki/Matthew_Koma'),\r\n" + 
						"	('Ecstatic', 'https://hardstyle.fandom.com/de/wiki/Ecstatic'),\r\n" + 
						"	('Krigarè', 'https://www.facebook.com/krigaremusic'),\r\n" + 
						"	('Deepack', 'https://en.wikipedia.org/wiki/Deepack'),\r\n" + 
						"	('Kaskade', 'https://en.wikipedia.org/wiki/Kaskade'),\r\n" + 
						"	('Snowfear', 'https://www.facebook.com/snowfearmusic/'),\r\n" + 
						"	('Distance (Snowfear)', 'https://www.facebook.com/snowfearmusic/'),\r\n" + 
						"	('Chaoz', 'https://www.facebook.com/Chaoz-132372097158618/'),\r\n" + 
						"	('Imany', 'https://de.wikipedia.org/wiki/Imany'),\r\n" + 
						"	('Interfearence', 'https://interfearence.nl/'),\r\n" + 
						"	('KELTEK', 'https://keltek.nl/'),\r\n" + 
						"	('MVTATE', 'https://de-de.facebook.com/mvtatemusic/'),\r\n" + 
						"	('Sylence', 'https://www.facebook.com/DJSylenceMusic'),\r\n" + 
						"	('MC DL', 'https://www.facebook.com/MCDL.NL/'),\r\n" + 
						"	('Energyzed', 'https://www.facebook.com/EnergyzedMusic'),\r\n" + 
						"	('Commercial Club Crew', 'https://www.discogs.com/de/artist/331205-Commercial-Club-Crew'),\r\n" + 
						"	('Ezenia', 'https://www.facebook.com/ezeniamusic/'),\r\n" + 
						"	('Alan Walker', 'https://en.wikipedia.org/wiki/Alan_Walker_(music_producer)'),\r\n" + 
						"	('Rank 1', 'https://en.wikipedia.org/wiki/Rank_1'),\r\n" + 
						"	('Zany', 'https://en.wikipedia.org/wiki/DJ_Zany'),\r\n" + 
						"	('DJ Justin Murta', 'https://justinmurta.com/'),\r\n" + 
						"	('Ovylarock', 'https://www.facebook.com/Ovylarock/'),\r\n" + 
						"	('TCM', 'https://www.facebook.com/TomCrawfordMusic/'),\r\n" + 
						"	('David Spekter', 'https://www.facebook.com/DavidSpekter/'),\r\n" + 
						"	('Porter Robinson', 'https://en.wikipedia.org/wiki/Porter_Robinson'),\r\n" + 
						"	('Dillytek', 'https://hardstyle.fandom.com/de/wiki/Dillytek'),\r\n" + 
						"	('Euphorizer', 'https://www.facebook.com/euphorizer'),\r\n" + 
						"	('Firelite', 'https://www.facebook.com/firelitemusic'),\r\n" + 
						"	('Christian Carlucci', 'https://www.discogs.com/de/artist/5746826-Christian-Carlucci'),\r\n" + 
						"	('Jonathan Mendelsohn', 'https://www.jonathan-mendelsohn.com/'),\r\n" + 
						"	('Forever Lost', 'https://www.facebook.com/Foreverlostmedia'),\r\n" + 
						"	('James Stefano', 'https://www.facebook.com/OfficialJamesStefano'),\r\n" + 
						"	('Anklebreaker', 'https://www.facebook.com/AnklebreakerOfficial/'),\r\n" + 
						"	('Arch FX (Deny Light)', 'https://www.facebook.com/DenyLightMusic/'),\r\n" + 
						"	('Deny Light', 'https://www.facebook.com/DenyLightMusic/'),\r\n" + 
						"	('ANDY SVGE', 'https://www.djandysvge.com/'),\r\n" + 
						"	('Armin van Buuren', 'https://www.arminvanbuuren.com/'),\r\n" + 
						"	('Vini Vici', 'https://en.wikipedia.org/wiki/Sesto_Sento#Vini_Vici'),\r\n" + 
						"	('Hilight Tribe', 'http://hilighttribe.fr/en/accueil-4/'),\r\n" + 
						"	('Festuca', 'https://hardstyle.fandom.com/de/wiki/Festuca'),\r\n" + 
						"	('Sick Individuals', 'https://en.wikipedia.org/wiki/Sick_Individuals'),\r\n" + 
						"	('Axwell', 'https://en.wikipedia.org/wiki/Axwell'),\r\n" + 
						"	('Taylr Renee', 'https://monstercat.fandom.com/wiki/Taylr_Renee'),\r\n" + 
						"	('Pherato', 'https://hardstyle.fandom.com/de/wiki/Pherato'),\r\n" + 
						"	('Rebourne', 'https://hardstyle.fandom.com/de/wiki/Rebourne'),\r\n" + 
						"	('Dave Revan', 'https://www.facebook.com/DaveRevanOfficial'),\r\n" + 
						"	('In-Rush', 'https://www.facebook.com/InrushOfficial/'),\r\n" + 
						"	('Nick Novity', 'https://hardstyle.fandom.com/de/wiki/Nick_Novity'),\r\n" + 
						"	('Demi Kanon', 'https://www.facebook.com/officialdemikanon/'),\r\n" + 
						"	('Dzeko', 'https://en.wikipedia.org/wiki/Dzeko_(DJ)'),\r\n" + 
						"	('Post Malone', 'https://en.wikipedia.org/wiki/Post_Malone'),\r\n" + 
						"	('Preme', 'https://en.wikipedia.org/wiki/Preme'),\r\n" + 
						"	('The Originalz', 'https://www.facebook.com/TheOriginalzHardstyle/'),\r\n" + 
						"	('Snowstylez', 'https://www.facebook.com/snowstylez/'),\r\n" + 
						"	('Linkin Park', 'https://en.wikipedia.org/wiki/Linkin_Park'),\r\n" + 
						"	('Steve Aoki', 'https://en.wikipedia.org/wiki/Steve_Aoki'),\r\n" + 
						"	('D-Charged', 'https://www.facebook.com/DCharged'),\r\n" + 
						"	('Martyr', 'https://www.facebook.com/MartyrHardstyle/'),\r\n" + 
						"	('MissJudged', 'https://www.facebook.com/MissJudgedOfficial/'),\r\n" + 
						"	('Azul DJ', 'https://www.facebook.com/AzulSVR/'),\r\n" + 
						"	('Crew Cardinal', 'https://de.wikipedia.org/wiki/Crew_Cardinal'),\r\n" + 
						"	('Syrin', 'https://www.youtube.com/user/HappyUnionGames'),\r\n" + 
						"	('Beatcaster', 'https://www.facebook.com/thebeatcaster'),\r\n" + 
						"	('Ember Island', 'https://www.facebook.com/emberisIand'),\r\n" + 
						"	('Dash Berlin', 'https://en.wikipedia.org/wiki/Dash_Berlin'),\r\n" + 
						"	('Stormerz', 'https://www.facebook.com/stormerzmusic'),\r\n" + 
						"	('Solstice', 'https://www.facebook.com/SolsticeHard'),\r\n" + 
						"	('Philip Matta', 'https://www.facebook.com/PhilipMattaMusic/'),\r\n" + 
						"	('Timekeeperz', 'https://www.facebook.com/Timekeeperz'),\r\n" + 
						"	('Otto Knows', 'https://en.wikipedia.org/wiki/Otto_Knows'),\r\n" + 
						"	('Nino Lucarelli', 'https://ninolucarelli.com/'),\r\n" + 
						"	('Barthezz', 'https://en.wikipedia.org/wiki/Bart_Claessen'),\r\n" + 
						"	('Luca Antolini', 'https://www.facebook.com/LucaAntoliniMusic/'),\r\n" + 
						"	('Oryon', 'https://www.facebook.com/OryonOficial'),\r\n" + 
						"	('Mako', 'https://itsmako.com/'),\r\n" + 
						"	('Mattias Welin', 'https://www.facebook.com/mattiaswelinofficial/'),\r\n" + 
						"	('R.I.O', 'https://en.wikipedia.org/wiki/R.I.O.'),\r\n" + 
						"	('Nicco', 'https://de.wikipedia.org/wiki/Nicco'),\r\n" + 
						"	('Bass Prototype', 'https://www.facebook.com/bassprototype/'),\r\n" + 
						"	('Tempest', 'http://www.tempest-hardstyle.de/'),\r\n" + 
						"	('Eric Prydz', 'https://en.wikipedia.org/wiki/Eric_Prydz'),\r\n" + 
						"	('Safri Duo', 'https://en.wikipedia.org/wiki/Safri_Duo'),\r\n" + 
						"	('BAQ', 'https://www.facebook.com/BAQDJ/'),\r\n" + 
						"	('Re-con', 'https://www.discogs.com/artist/198662-Mike-Di-Scala'),\r\n" + 
						"	('Matthew Steeper', 'http://www.matthewsteeper.com/'),\r\n" + 
						"	('Devotion', 'https://www.facebook.com/DevotionFR'),\r\n" + 
						"	('Farisha', 'https://www.looperman.com/users/profile/394401'),\r\n" + 
						"	('Handsup Playerz', 'https://open.spotify.com/artist/6lk2VHzhFSugOZYczgMzis'),\r\n" + 
						"	('Fluxstyle', 'https://www.facebook.com/fluxstylemusic/'),\r\n" + 
						"	('Galantis', 'https://en.wikipedia.org/wiki/Galantis'),\r\n" + 
						"	('Bellini', 'https://en.wikipedia.org/wiki/Bellini_(German_band)'),\r\n" + 
						"	('Dua Lipa', 'https://en.wikipedia.org/wiki/Dua_Lipa'),\r\n" + 
						"	('Tim Berg (Avicii)', 'https://en.wikipedia.org/wiki/Avicii'),\r\n" + 
						"	('Avicii', 'https://en.wikipedia.org/wiki/Avicii'),\r\n" + 
						"	('Innerself', 'https://www.facebook.com/Innerselfmedia'),\r\n" + 
						"	('Navion', 'https://www.facebook.com/naviondj'),\r\n" + 
						"	('Misael Gauna', 'null'),\r\n" + 
						"	('Noctilucent', 'null'),\r\n" + 
						"	('Two Steps From Hell', 'https://en.wikipedia.org/wiki/Two_Steps_from_Hell'),\r\n" + 
						"	('Nizami Plus', 'https://www.discogs.com/de/artist/4612639-Nizami-Plus'),\r\n" + 
						"	('Chris Ponate', 'https://www.facebook.com/ChrisPonate'),\r\n" + 
						"	('Nontoxic (NTXC)', 'https://www.facebook.com/ntxcmusic'),\r\n" + 
						"	('Shawn Mendes', 'https://en.wikipedia.org/wiki/Shawn_Mendes'),\r\n" + 
						"	('Kygo', 'https://en.wikipedia.org/wiki/Kygo'),\r\n" + 
						"	('Unsenses', 'https://www.unsenses.com/'),\r\n" + 
						"	('OneRepublic', 'https://en.wikipedia.org/wiki/OneRepublic'),\r\n" + 
						"	('Galactixx', 'https://www.facebook.com/galactixx.dj'),\r\n" + 
						"	('Illuminize', 'https://www.facebook.com/illuminizedj'),\r\n" + 
						"	('Weldon', 'null'),\r\n" + 
						"	('Nomi', 'https://www.discogs.com/de/artist/5842985-Nomi-11'),\r\n" + 
						"	('Gostosa', 'https://www.discogs.com/de/artist/1574648-Gostosa'),\r\n" + 
						"	('Teardrops', 'https://www.facebook.com/Teardropzofficial'),\r\n" + 
						"	('Christina Novelli', 'https://en.wikipedia.org/wiki/Christina_Novelli'),\r\n" + 
						"	('Heatwavez', 'https://www.facebook.com/Heatwavezmedia/'),\r\n" + 
						"	('Kaevohia', 'https://www.facebook.com/Kaevohia/'),\r\n" + 
						"	('Hard Driver', 'https://www.facebook.com/HardDriverMusic/'),\r\n" + 
						"	('The Elite (Coone, Da Tweekaz, Harddriver)', 'https://www.discogs.com/de/artist/7896764-The-Elite-12'),\r\n" + 
						"	('Diandra Faye', 'https://www.facebook.com/diandra.burger'),\r\n" + 
						"	('Pulse', 'https://soundcloud.com/djpulsenl'),\r\n" + 
						"	('DJ Game', 'https://www.facebook.com/GameDJOfficial'),\r\n" + 
						"	('Trivecta', 'https://www.facebook.com/trivectamusic'),\r\n" + 
						"	('Monika Santucci', 'https://www.facebook.com/MonikaSantucci'),\r\n" + 
						"	('Lucas & Steve', 'https://www.facebook.com/LucasAndSteve'),\r\n" + 
						"	('Releazer', 'https://www.facebook.com/OfficialReleazer/'),\r\n" + 
						"	('Coldplay', 'https://en.wikipedia.org/wiki/Coldplay'),\r\n" + 
						"	('The Un4given', 'https://www.facebook.com/Un4givenOfficial'),\r\n" + 
						"	('A-Shock', 'https://www.facebook.com/A-Shock-243810792679163'),\r\n" + 
						"	('Emma Hewitt', 'https://en.wikipedia.org/wiki/Emma_Hewitt'),\r\n" + 
						"	('Elizsabeth', 'https://www.facebook.com/elizsabethmusic'),\r\n" + 
						"	('Dr Rude', 'http://www.dr-rude.nl/'),\r\n" + 
						"	('Fred Maple', 'https://www.facebook.com/fredmapleofficial/'),\r\n" + 
						"	('Dimitri Vegas & Like Mike', 'https://en.wikipedia.org/wiki/Dimitri_Vegas_%26_Like_Mike'),\r\n" + 
						"	('Wiz Khalifa', 'https://en.wikipedia.org/wiki/Wiz_Khalifa'),\r\n" + 
						"	('The Vision', 'https://www.facebook.com/TheVisionOfficial/?fref=ts'),\r\n" + 
						"	('Hard Projectz', 'https://soundcloud.com/hardprojectz'),\r\n" + 
						"	('Inverze', 'https://www.facebook.com/inverzeofficial'),\r\n" + 
						"	('DJ Breeze', 'https://en.wikipedia.org/wiki/Mark_Breeze'),\r\n" + 
						"	('Styles & Breeze', 'https://www.discogs.com/artist/198427-Styles-Breeze'),\r\n" + 
						"	('DJ Faderz', 'https://www.facebook.com/FaderzDJ'),\r\n" + 
						"	('Zedd', 'https://www.zedd.net/'),\r\n" + 
						"	('ADN Lewis', 'https://www.facebook.com/AdienLewis/')");		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  feelings");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE feelings (\r\n"
						+ "  id int NOT NULL,\r\n"
						+ "  name varchar(50) NOT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ") ");
		db.getConnection().createStatement().execute("DELETE FROM feelings");
		db.getConnection().createStatement()
				.execute("INSERT INTO feelings (id, name) VALUES\r\n" + 
						"	(1, '-'),\r\n" + 
						"	(28, 'aggressive'),\r\n" + 
						"	(18, 'angry'),\r\n" + 
						"	(15, 'anxious'),\r\n" + 
						"	(14, 'brave'),\r\n" + 
						"	(26, 'dreaming'),\r\n" + 
						"	(23, 'drinkmode'),\r\n" + 
						"	(10, 'energetic'),\r\n" + 
						"	(4, 'free'),\r\n" + 
						"	(11, 'fun'),\r\n" + 
						"	(21, 'grooving'),\r\n" + 
						"	(24, 'group'),\r\n" + 
						"	(27, 'happy'),\r\n" + 
						"	(22, 'hate'),\r\n" + 
						"	(16, 'hopeful'),\r\n" + 
						"	(3, 'in love'),\r\n" + 
						"	(20, 'lonely'),\r\n" + 
						"	(19, 'lost'),\r\n" + 
						"	(7, 'motivating (fitness)'),\r\n" + 
						"	(8, 'motivating (life)'),\r\n" + 
						"	(25, 'neutral'),\r\n" + 
						"	(17, 'painful'),\r\n" + 
						"	(5, 'partymode'),\r\n" + 
						"	(12, 'pessimistic'),\r\n" + 
						"	(6, 'rebellious'),\r\n" + 
						"	(2, 'sad'),\r\n" + 
						"	(29, 'soothing'),\r\n" + 
						"	(13, 'thankful'),\r\n" + 
						"	(30, 'Trance')");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  genres");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE genres (\r\n"
						+ "  id int  NOT NULL ,\r\n"
						+ "  name varchar(50) NOT NULL DEFAULT '0',\r\n" + "  parent int  DEFAULT NULL,\r\n"
						+ "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM genres");
		db.getConnection().createStatement()
				.execute("INSERT INTO genres (id, name, parent) VALUES\r\n" + 
						"	(1, 'EDM (Electronic Dance Music)', NULL),\r\n" + 
						"	(2, 'Hardstyle', 1),\r\n" + 
						"	(3, 'Psystyle', 2),\r\n" + 
						"	(4, 'Euphoric Hardstyle', 2),\r\n" + 
						"	(5, 'Rawstyle', 2),\r\n" + 
						"	(6, 'Rawphoric Hardstyle', 2),\r\n" + 
						"	(7, 'Classic Hardstyle', 2),\r\n" + 
						"	(8, 'Early Hardstyle', 2),\r\n" + 
						"	(9, 'Dubstyle', 2),\r\n" + 
						"	(10, 'Subground', 2),\r\n" + 
						"	(11, 'Pop', NULL),\r\n" + 
						"	(12, 'Electro', 1),\r\n" + 
						"	(13, 'Trap', 1),\r\n" + 
						"	(14, 'Trance', 1),\r\n" + 
						"	(115, 'Techno', 1),\r\n" + 
						"	(116, 'Dubstep', 1),\r\n" + 
						"	(117, 'House', 1),\r\n" + 
						"	(118, 'Drum & Bass', 1),\r\n" + 
						"	(119, 'Hardtrap', 13),\r\n" + 
						"	(120, 'Dream Trance', 14),\r\n" + 
						"	(121, 'Acid Trance', 14),\r\n" + 
						"	(122, 'Hard Trance', 14),\r\n" + 
						"	(123, 'Uplifting Trance', 14),\r\n" + 
						"	(124, 'Vocal Trance', 14),\r\n" + 
						"	(125, 'Psytrance (Goa)', 115),\r\n" + 
						"	(126, 'Tech Trance', 14),\r\n" + 
						"	(127, 'Schranz', 115),\r\n" + 
						"	(128, 'Acid Techno', 115),\r\n" + 
						"	(130, 'Ghettotech', 115),\r\n" + 
						"	(131, 'Nortec', 115),\r\n" + 
						"	(133, 'Synth-pop', 11),\r\n" + 
						"	(134, 'Toytown Techno', 115),\r\n" + 
						"	(135, 'Hardcore', 115),\r\n" + 
						"	(136, 'Breakcore', 135),\r\n" + 
						"	(138, 'Frenchcore', 135),\r\n" + 
						"	(139, 'Gabber', 135),\r\n" + 
						"	(140, 'Happy Hardcore', 135),\r\n" + 
						"	(141, 'Speedcore', 135),\r\n" + 
						"	(145, 'Minimal Techno', 115),\r\n" + 
						"	(146, 'Dub-Techno', 115),\r\n" + 
						"	(147, 'Hands Up', 115),\r\n" + 
						"	(148, 'Jumpstyle', 115),\r\n" + 
						"	(151, 'Chillstep', 116),\r\n" + 
						"	(152, 'Brostep', 116),\r\n" + 
						"	(153, 'Tech House', 117),\r\n" + 
						"	(154, 'Deep House', 117),\r\n" + 
						"	(155, 'Acid House', 117),\r\n" + 
						"	(156, 'Big-Room', 117),\r\n" + 
						"	(157, 'Afro House', 117),\r\n" + 
						"	(158, 'Electro-House', 117),\r\n" + 
						"	(159, 'Dirty House', 158),\r\n" + 
						"	(161, 'Future House', 117),\r\n" + 
						"	(162, 'Disco House', 117),\r\n" + 
						"	(163, 'Eurodance', 117),\r\n" + 
						"	(164, 'Chicago House', 117),\r\n" + 
						"	(165, 'Funky House', 117),\r\n" + 
						"	(166, 'Garage House', 117),\r\n" + 
						"	(167, 'Ghetto Tech', 117),\r\n" + 
						"	(168, 'Ibiza House', 117),\r\n" + 
						"	(169, 'Kwaito', 117),\r\n" + 
						"	(170, 'Latin House', 117),\r\n" + 
						"	(171, 'Micro House', 117),\r\n" + 
						"	(172, 'Minimal House', 117),\r\n" + 
						"	(173, 'Speed Garage', 117),\r\n" + 
						"	(174, 'Progressive House', 117),\r\n" + 
						"	(175, 'Bass House', 117),\r\n" + 
						"	(176, 'Tribal House', 117),\r\n" + 
						"	(177, 'Tropical House', 117),\r\n" + 
						"	(178, '2 Step', 117),\r\n" + 
						"	(179, 'Vocal House', 117),\r\n" + 
						"	(180, 'Hip Hop', NULL),\r\n" + 
						"	(181, 'Rap', 180),\r\n" + 
						"	(182, 'Alternative Rap', 181),\r\n" + 
						"	(183, 'Coutry-Rap', 181),\r\n" + 
						"	(184, 'Cumbia Rap', 181),\r\n" + 
						"	(185, 'Freestyle Rap', 181),\r\n" + 
						"	(186, 'Gangsta Rap', 181),\r\n" + 
						"	(187, 'Hardcore Rap', 181),\r\n" + 
						"	(188, 'Latin Rap', 181),\r\n" + 
						"	(189, 'Merenrap', 181),\r\n" + 
						"	(190, 'Old School Rap', 181),\r\n" + 
						"	(191, 'Underground Rap', 181),\r\n" + 
						"	(192, 'West Coast Rap', 181),\r\n" + 
						"	(194, 'Bounce', 180),\r\n" + 
						"	(195, 'Chap Hop', 180),\r\n" + 
						"	(196, 'Christian Hip Hop', 180),\r\n" + 
						"	(197, 'Conscious Hip Hop', 180),\r\n" + 
						"	(198, 'Crunkcore', 180),\r\n" + 
						"	(199, 'Dirty South', 180),\r\n" + 
						"	(200, 'East Coast', 180),\r\n" + 
						"	(201, 'Brick City Club', 200),\r\n" + 
						"	(202, 'Hardcore Hip Hop', 200),\r\n" + 
						"	(203, 'Mafioso Rap', 200),\r\n" + 
						"	(204, 'New Jersey Hip Hop', 200),\r\n" + 
						"	(205, 'G-Funk', 180),\r\n" + 
						"	(206, 'Hyphy', 180),\r\n" + 
						"	(207, 'Industrial Hip Hop', 180),\r\n" + 
						"	(208, 'Instrumental Hip Hop', 180),\r\n" + 
						"	(209, 'Midwest Hip Hop', 180),\r\n" + 
						"	(210, 'Chicago Hip Hop', 209),\r\n" + 
						"	(211, 'Detroid Hip Hop', 209),\r\n" + 
						"	(212, 'St. Louis Hip Hop', 209),\r\n" + 
						"	(213, 'Twin Cities Hip Hop', 209),\r\n" + 
						"	(214, 'Horrorcore', 209),\r\n" + 
						"	(215, 'Motswako', 180),\r\n" + 
						"	(216, 'Nerdcore', 180),\r\n" + 
						"	(217, 'New Jack Swing', 180),\r\n" + 
						"	(218, 'New School Hip Hop', 180),\r\n" + 
						"	(219, 'Turntablism', 180),\r\n" + 
						"	(220, 'Classical', NULL),\r\n" + 
						"	(222, 'Avant-Garde', 220),\r\n" + 
						"	(223, 'Ballet', 220),\r\n" + 
						"	(224, 'Baroque', 220),\r\n" + 
						"	(225, 'Cantata', 220),\r\n" + 
						"	(226, 'Chamber Music', 220),\r\n" + 
						"	(227, 'Chant', 220),\r\n" + 
						"	(228, 'Choral', 220),\r\n" + 
						"	(229, 'Classical Crossover', 220),\r\n" + 
						"	(230, 'Contemporary Classical', 220),\r\n" + 
						"	(231, 'Early Music', 220),\r\n" + 
						"	(232, 'Expressionist', 220),\r\n" + 
						"	(233, 'High Classical', 220),\r\n" + 
						"	(234, 'Impressionist', 220),\r\n" + 
						"	(235, 'Mass Requiem', 220),\r\n" + 
						"	(236, 'Medieval', 220),\r\n" + 
						"	(237, 'Minimalism', 220),\r\n" + 
						"	(238, 'Modern Composition', 220),\r\n" + 
						"	(239, 'Opera', 220),\r\n" + 
						"	(240, 'Oratorio', 220),\r\n" + 
						"	(241, 'Orchestral', 220),\r\n" + 
						"	(242, 'Organum', 220),\r\n" + 
						"	(243, 'Renaissance', 220),\r\n" + 
						"	(244, 'Romantic', 220),\r\n" + 
						"	(245, 'Early Romantic', 244),\r\n" + 
						"	(246, 'Later Romantic', 244),\r\n" + 
						"	(247, 'Sonata', 220),\r\n" + 
						"	(248, 'Symphonic', 220),\r\n" + 
						"	(249, 'Wedding Music', 220),\r\n" + 
						"	(250, 'Folk', NULL),\r\n" + 
						"	(251, 'American Folk Revival', 250),\r\n" + 
						"	(252, 'Anti-Folk', 250),\r\n" + 
						"	(253, 'British Folk Revival', 250),\r\n" + 
						"	(254, 'Contemporary Folk', 250),\r\n" + 
						"	(255, 'Freak Folk', 250),\r\n" + 
						"	(256, 'Indie Folk', 250),\r\n" + 
						"	(257, 'Industrial Folk', 250),\r\n" + 
						"	(258, 'Neofolk', 250),\r\n" + 
						"	(259, 'Progressive Folk', 250),\r\n" + 
						"	(260, 'Psychedelic Folk', 250),\r\n" + 
						"	(261, 'Sung Poetry', 250),\r\n" + 
						"	(262, 'Techno-Folk', 250),\r\n" + 
						"	(263, 'Folk Punk', 250),\r\n" + 
						"	(264, 'Folk Blues', 250),\r\n" + 
						"	(265, 'Turbofolk', 250),\r\n" + 
						"	(266, 'Folk-Rock', 250),\r\n" + 
						"	(267, 'Traditional Folk', 250),\r\n" + 
						"	(268, 'Celtic Folk', 250),\r\n" + 
						"	(269, 'Alternative Folk', 250),\r\n" + 
						"	(270, 'German Folk (Schlager)', 250),\r\n" + 
						"	(271, 'Cantopop', 11),\r\n" + 
						"	(272, 'Elektro-Pop', 11),\r\n" + 
						"	(273, 'Europop', 272),\r\n" + 
						"	(274, 'Dance-Pop', 272),\r\n" + 
						"	(275, 'Synthie-Pop', 272),\r\n" + 
						"	(276, 'Italo Pop', 11),\r\n" + 
						"	(277, 'J-Pop', 11),\r\n" + 
						"	(278, 'K-Pop', 11),\r\n" + 
						"	(279, 'Latin Pop', 11),\r\n" + 
						"	(280, 'Operatic Pop', 11),\r\n" + 
						"	(281, 'Progressive Pop', 11),\r\n" + 
						"	(282, 'Sophisti Pop', 11),\r\n" + 
						"	(283, 'Space-Age Pop', 11),\r\n" + 
						"	(284, 'Sunshine Pop', 11),\r\n" + 
						"	(285, 'Discopop', 11),\r\n" + 
						"	(286, 'Euro-Disco', 285),\r\n" + 
						"	(287, 'Teen Pop', 11),\r\n" + 
						"	(288, 'Barock Pop', 11),\r\n" + 
						"	(289, 'Country Pop', 11),\r\n" + 
						"	(290, 'Indie Pop', 11),\r\n" + 
						"	(291, 'Jangle Pop', 11),\r\n" + 
						"	(292, 'New Wave', 11),\r\n" + 
						"	(293, 'Pop-Punk', 11),\r\n" + 
						"	(294, 'Pop-Rap', 11),\r\n" + 
						"	(295, 'Pop-Rock', 11),\r\n" + 
						"	(298, 'Power Pop', 11),\r\n" + 
						"	(299, 'Psychedelic Pop', 11),\r\n" + 
						"	(300, 'Gospel', NULL),\r\n" + 
						"	(301, 'Smooth Jazz', 11),\r\n" + 
						"	(302, 'Christian Pop', 11),\r\n" + 
						"	(303, 'Jazz', NULL),\r\n" + 
						"	(304, 'Acid Jazz', 303),\r\n" + 
						"	(305, 'Avant-Garde Jazz', 303),\r\n" + 
						"	(306, 'Big Band', 303),\r\n" + 
						"	(307, 'Blue Note', 303),\r\n" + 
						"	(308, 'Contemporary Jazz', 303),\r\n" + 
						"	(309, 'Crossover Jazz', 303),\r\n" + 
						"	(310, 'Dixieland', 303),\r\n" + 
						"	(311, 'Ethio Jazz', 303),\r\n" + 
						"	(312, 'Fusion Jazz', 303),\r\n" + 
						"	(313, 'Gypsy Jazz', 303),\r\n" + 
						"	(314, 'Latin Jazz', 303),\r\n" + 
						"	(315, 'Ragtime', 303),\r\n" + 
						"	(316, 'Trad Jazz', 303),\r\n" + 
						"	(317, 'RnB', NULL),\r\n" + 
						"	(318, 'Soul', 317),\r\n" + 
						"	(319, 'Funk', 317),\r\n" + 
						"	(320, 'Modern Soul', 318),\r\n" + 
						"	(321, 'Neo Soul', 318),\r\n" + 
						"	(322, 'Northern Soul', 318),\r\n" + 
						"	(323, 'Psychedelic Soul', 318),\r\n" + 
						"	(324, 'Southern Soul', 318),\r\n" + 
						"	(325, 'Soul Blues', 318),\r\n" + 
						"	(326, 'Metal', NULL),\r\n" + 
						"	(327, 'Heavy Metal', 326),\r\n" + 
						"	(328, 'Glam Metal', 326),\r\n" + 
						"	(329, 'Power Metal', 326),\r\n" + 
						"	(330, 'British Heave Metal', 326),\r\n" + 
						"	(331, 'Trash Metal', 326),\r\n" + 
						"	(332, 'Black Metal', 331),\r\n" + 
						"	(333, 'Death Metal', 331),\r\n" + 
						"	(334, 'Groove Metal', 331),\r\n" + 
						"	(335, 'Nu Metal', 334),\r\n" + 
						"	(336, 'Speed Metal', 326),\r\n" + 
						"	(337, 'Gothic Metal', 326),\r\n" + 
						"	(338, 'Industrial Metal', 326),\r\n" + 
						"	(339, 'Folk Metal', 326),\r\n" + 
						"	(340, 'Grindcore', 326),\r\n" + 
						"	(341, 'Deathcore', 326),\r\n" + 
						"	(342, 'Pagan Metal', 326),\r\n" + 
						"	(343, 'Symphonic Metal', 326),\r\n" + 
						"	(344, 'Viking Metal', 326),\r\n" + 
						"	(345, 'White Metal', 326),\r\n" + 
						"	(346, 'Sludge Metal', 326),\r\n" + 
						"	(348, 'RocknRoll', NULL),\r\n" + 
						"	(349, 'Rockabilly', 348),\r\n" + 
						"	(350, 'Glam Rock', 348),\r\n" + 
						"	(351, 'Psychedelic Rock', 348),\r\n" + 
						"	(352, 'Gothic Rock', 351),\r\n" + 
						"	(353, 'Death Rock', 348),\r\n" + 
						"	(354, 'Hard Rock', 348),\r\n" + 
						"	(355, 'Punk', NULL),\r\n" + 
						"	(356, 'Grunge', 355),\r\n" + 
						"	(357, 'Hardcore Punk', 355),\r\n" + 
						"	(358, 'Anarcho Punk', 355),\r\n" + 
						"	(361, 'Psychobilly', 355),\r\n" + 
						"	(362, 'Reggae', NULL),\r\n" + 
						"	(363, '2-Tone', 362),\r\n" + 
						"	(364, 'Dancehall', 362),\r\n" + 
						"	(365, 'Lovers Rock', 362),\r\n" + 
						"	(366, 'Ska', 362),\r\n" + 
						"	(367, 'Blues', NULL),\r\n" + 
						"	(368, 'Acoustic Blues', 367),\r\n" + 
						"	(369, 'African Blues', 367),\r\n" + 
						"	(370, 'Blues Rock', 367),\r\n" + 
						"	(371, 'Blues Shouter', 367),\r\n" + 
						"	(372, 'British Blues', 367),\r\n" + 
						"	(373, 'Canadian Blues', 367),\r\n" + 
						"	(374, 'Chicago Blues', 367),\r\n" + 
						"	(375, 'Classic Female Blues', 367),\r\n" + 
						"	(376, 'Classic Blues', 367),\r\n" + 
						"	(377, 'Contemporary Blues', 367),\r\n" + 
						"	(378, 'Country Blues', 367),\r\n" + 
						"	(379, 'Delta Blues', 367),\r\n" + 
						"	(380, 'Detroid Blues', 367),\r\n" + 
						"	(381, 'Electric Blues', 367),\r\n" + 
						"	(386, 'Gospel Blues', 300),\r\n" + 
						"	(387, 'Harmonica Blues', 367),\r\n" + 
						"	(388, 'Hill Country Blues', 367),\r\n" + 
						"	(389, 'Hokum Blues', 367),\r\n" + 
						"	(390, 'Jazz Blues', 367),\r\n" + 
						"	(391, 'Jump Blues', 367),\r\n" + 
						"	(392, 'Kansas City Blues', 367),\r\n" + 
						"	(393, 'Louisiana Blues', 367),\r\n" + 
						"	(394, 'Memphis Blues', 367),\r\n" + 
						"	(395, 'Modern Blues', 367),\r\n" + 
						"	(396, 'New Orlean Blues', 367),\r\n" + 
						"	(397, 'NY Blues', 367),\r\n" + 
						"	(398, 'Piano Blues', 367),\r\n" + 
						"	(400, 'Piedmont Blues', 367),\r\n" + 
						"	(401, 'Punk Blues', 367),\r\n" + 
						"	(402, 'Ragtime Blues', 367),\r\n" + 
						"	(403, 'Rythm Blues', 367),\r\n" + 
						"	(405, 'St. Louis Blues', 367),\r\n" + 
						"	(406, 'Swamp Blues', 367),\r\n" + 
						"	(407, 'Texas Blues', 367),\r\n" + 
						"	(408, 'Urban Blues', 367),\r\n" + 
						"	(409, 'Vandeville', 367),\r\n" + 
						"	(410, 'West Coast Blues', 367),\r\n" + 
						"	(411, 'Zydeco', 367)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  labels");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE labels (\r\n"
						+ "  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  name varchar(50) DEFAULT '0',\r\n" + "  link varchar(100) DEFAULT NULL,\r\n"
						+ "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM labels");
		db.getConnection().createStatement().execute("INSERT INTO labels (name, link) VALUES\r\n"
				+ "	('DJS Records', 'https://www.discogs.com/de/label/16547-DJS-Records'),\r\n" + 
				"	('Fusion Records', 'https://en.wikipedia.org/wiki/Fusion_Records'),\r\n" + 
				"	('Scantraxx', 'https://de.wikipedia.org/wiki/Scantraxx'),\r\n" + 
				"	('Seismic Records', 'https://www.discogs.com/label/12726-Seismic-Records-2'),\r\n" + 
				"	('X-Rate Records', 'https://www.discogs.com/de/label/18155-X-Rate-Records'),\r\n" + 
				"	('ID&T', 'https://de.wikipedia.org/wiki/ID%26T'),\r\n" + 
				"	('Sensation Black', 'https://de.wikipedia.org/wiki/Sensation_(Dance-Event)#Black_(bis_2007_Sensation_Black)'),\r\n" + 
				"	('Dutch Master Works', 'https://hardstyle.fandom.com/de/wiki/Dutch_Master_Works'),\r\n" + 
				"	('Minus is More', 'https://www.minusismore.com/'),\r\n" + 
				"	('TiLLT Records', 'https://hardstyle.fandom.com/de/wiki/TiLLT!_Records'),\r\n" + 
				"	('Theracords', 'https://hardstyle.fandom.com/de/wiki/Theracords'),\r\n" + 
				"	('Keep It Up Music', 'https://www.keepitupmusic.com/'),\r\n" + 
				"	('Digital Age', 'https://hardstyle.fandom.com/de/wiki/Digital:Age'),\r\n" + 
				"	('WE R', 'http://www.wer-music.com/'),\r\n" + 
				"	('Q-Dance Records', 'https://www.q-dance.com/en/'),\r\n" + 
				"	('Spirit Of Hardstyle', 'https://hardstyle.fandom.com/de/wiki/Spirit_Of_Hardstyle'),\r\n" + 
				"	('Art of Creation', 'https://artofcreation.com/'),\r\n" + 
				"	('Roughstate', 'https://hardstyle.fandom.com/de/wiki/Roughstate'),\r\n" + 
				"	('Free Release', NULL),\r\n" + 
				"	('Dirty Workz', 'https://www.dirtyworkz.com/'),\r\n" + 
				"	('Blutonium Records', 'blutonium.de/'),\r\n" + 
				"	('Hardnation Records', 'https://www.discogs.com/de/label/15274-Hardnation-Records'),\r\n" + 
				"	('Tunnel Records', 'https://de.wikipedia.org/wiki/Tunnel_Records'),\r\n" + 
				"	('Hardbeatz Records', 'https://www.facebook.com/HardBeatzRecords'),\r\n" + 
				"	('Harder Stylez Records', 'https://de-de.facebook.com/harderstylezrecords'),\r\n" + 
				"	('Davaro Records', 'https://www.discogs.com/de/label/194780-Davaro-Records'),\r\n" + 
				"	('Neptun Records', 'http://www.neptunerecords.com/'),\r\n" + 
				"	('Musical Awareness Records', 'https://www.discogs.com/label/774404-Musical-Awareness-Records'),\r\n" + 
				"	('Infected Records', 'https://www.facebook.com/InfectedRecords030/'),\r\n" + 
				"	('EDM-Unity', 'https://www.facebook.com/unityEDM'),\r\n" + 
				"	('Spoontech Records', 'https://spoontechrecords.com/'),\r\n" + 
				"	('Activa Records', 'www.activarecords.com/index/Home.html'),\r\n" + 
				"	('BLQ Records', 'https://www.discogs.com/de/label/5561-Blq-Records'),\r\n" + 
				"	('Dance Pollution', 'https://www.discogs.com/de/label/1181-Dance-Pollution'),\r\n" + 
				"	('Saifam', 'https://www.saifam.com/'),\r\n" + 
				"	('Titanic Records', 'http://www.titanicrecords.com/'),\r\n" + 
				"	('Zanzalabs', 'https://hardstyle.fandom.com/de/wiki/Zanzalabs'),\r\n" + 
				"	('Scantraxx Italy', 'https://www.scantraxx.com/music/labels/scantraxx-italy/'),\r\n" + 
				"	('Italian Hardstyle', 'https://hardstyle.fandom.com/de/wiki/Italian_Hardstyle'),\r\n" + 
				"	('Unite Records', 'https://hardstyle.fandom.com/de/wiki/Unite_Records'),\r\n" + 
				"	('White Blood Records', 'https://www.discogs.com/label/142034-White-Blood-Records'),\r\n" + 
				"	('Bionic Digital Recordings', 'https://www.discogs.com/de/label/171257-Bionic-Digital'),\r\n" + 
				"	('Gearbox Digital', 'https://www.gearboxdigital.com/'),\r\n" + 
				"	('Darkstyle Traxx', 'https://www.discogs.com/de/label/38996-Darkstyle-Traxx'),\r\n" + 
				"	('Hot Score Records', 'https://www.discogs.com/de/label/11811-Hot-Score-Records'),\r\n" + 
				"	('Austrocore', 'https://www.discogs.com/de/label/46946-Austrocore-Productions'),\r\n" + 
				"	('Hardfusion', NULL),\r\n" + 
				"	('Hard Music Records', 'https://www.hardmusicrecords.com/'),\r\n" + 
				"	('D3-Structive Records', 'https://hardstyle.fandom.com/de/wiki/D3-Structive_Records'),\r\n" + 
				"	('Sikkdome', 'https://de-de.facebook.com/sikkdome/'),\r\n" + 
				"	('ETX Editiontraxx', 'www.etx.ch/'),\r\n" + 
				"	('Sector Beatz', 'https://www.sector-beatz.com/'),\r\n" + 
				"	('Swiss Masterworks', 'https://wl39www441.webland.ch/'),\r\n" + 
				"	('Hexablast Records', 'www.hexablast.com/'),\r\n" + 
				"	('Invaders Records', 'https://www.discogs.com/de/label/257242-Invaders-Records'),\r\n" + 
				"	('null', 'null'),\r\n" + 
				"	('Hard With Style', 'https://www.discogs.com/de/label/642209-HARDwithSTYLE'),\r\n" + 
				"	('A2 Records', 'https://www.scantraxx.com/music/labels/a2-records/'),\r\n" + 
				"	('End of Line Media', 'https://www.discogs.com/de/label/352185-End-of-Line'),\r\n" + 
				"	('Hard Classic Records', 'https://www.discogs.com/de/label/1401361-Hard-Classic-Recordz'),\r\n" + 
				"	('Be Yourselfe Music', 'https://www.discogs.com/de/label/46006-Be-Yourself-Music'),\r\n" + 
				"	('Lose Control Music', 'https://hardstyle.fandom.com/de/wiki/Lose_Control_Music'),\r\n" + 
				"	('Revealed Recordings', 'https://www.revealedrecordings.com/'),\r\n" + 
				"	('Zoo Digital', 'https://www.discogs.com/de/label/120063-Zoo-Digital'),\r\n" + 
				"	('Zooland Records', 'https://www.discogs.com/label/49234-Zooland-Records'),\r\n" + 
				"	('Gearbox Euphoria', 'https://hardstyle.fandom.com/de/wiki/Gearbox_Euphoria'),\r\n" + 
				"	('Derailed Traxx', 'https://hardstyle.fandom.com/de/wiki/Derailed_Traxx'),\r\n" + 
				"	('X-Bone Records', 'http://xbone.nl/')");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  languages");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE languages (\r\n"
						+ "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  Name varchar(50) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM languages");
		db.getConnection().createStatement().execute("INSERT INTO languages (Name) VALUES\r\n" + "	('English'),\r\n"
				+ "	('French'),\r\n" + "	('German'),\r\n" + "	('Italian'),\r\n" + "	('Spanish')");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  playlists");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE playlists (\r\n"
						+ "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  name varchar(50) NOT NULL DEFAULT 'New Playlist',\r\n"
						+ "  userid int  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (id)\r\n" + "  )");
		db.getConnection().createStatement().execute("DELETE FROM playlists");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  songs");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE songs (\r\n"
						+ "  id bigint  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  releasedate int DEFAULT NULL,\r\n"
						+ "  name varchar(50) DEFAULT NULL,\r\n"
						+ "  artist int  DEFAULT NULL,\r\n"
						+ "  album int  DEFAULT NULL,\r\n"
						+ "  genre int  DEFAULT NULL,\r\n"
						+ "  label int  DEFAULT NULL,\r\n"
						+ "  language int  DEFAULT NULL,\r\n"
						+ "  yt_link varchar(50) DEFAULT NULL,\r\n"
						+ "  sf_link varchar(50) DEFAULT NULL,\r\n"
						+ "  sc_link varchar(50) DEFAULT NULL,\r\n"
						+ "  PRIMARY KEY (id)\r\n" + ") ");
		
		
		try {
			db.getConnection().createStatement().execute("DELETE FROM songs");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement().execute(
				"INSERT INTO songs (releasedate, name, artist, album, genre, label, language, yt_link, sf_link, sc_link) VALUES\r\n"
						+ "(20190829, 'Untamable', 2, 1, 2, 17, 2, 'NCPAwdRvNRc', '6VQye74oX8FYIXUKI7feiG', '672606881'),\r\n" + 
						"	(20191004, 'Army of Fire', 3, 1, 4, 15, 2, 'O2PLv1Fn004', '5QK32vB1hmlEe1ruP1usFk', '644443566'),\r\n" + 
						"	(20191010, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', '0WieTfhf6N0alEhBpSiOtd', '691089472'),\r\n" + 
						"	(0, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', '0WieTfhf6N0alEhBpSiOtd', '691089472'),\r\n" + 
						"	(0, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', '0WieTfhf6N0alEhBpSiOtd', '691089472'),\r\n" + 
						"	(0, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', '0WieTfhf6N0alEhBpSiOtd', '691089472'),\r\n" + 
						"	(0, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', '0WieTfhf6N0alEhBpSiOtd', '691089472'),\r\n" + 
						"	(20190405, 'Be My Guide', 5, 1, 6, 20, 2, 'Cp7-LoQXhEU', '6Kf5Rtpdx6wpVSn8TvVKFg', '597867123'),\r\n" + 
						"	(20151012, 'Number 23', 17, 1, 3, 31, 2, '4mFWu0UQ7Ng', '4yxF0kylP9O26VbyqIkxmu', 'null'),\r\n" + 
						"	( 20160101, 'Pandora (Psy Edit)', 19, 1, 3, 19, 2, 'igkA7VUcxk8', 'null', '295787652'),\r\n" + 
						"	( 20160529, 'Blame It On The Psytrance', 10, 1, 1, 19, 2, 'clLhfnsk7C8', 'null', '266442257'),\r\n" + 
						"	( 20170201, 'The Project', 5, 1, 5, 20, 2, 'JUGOvsXPIOA', '2fQcm2pX0XQjOrLxAdYxJJ', '302134474'),\r\n" + 
						"	( 20170407, 'Psychedelica', 21, 1, 3, 57, 2, 'LKq10jHDP6I', '71HPOrlMPtrPtSf5tPGK4N', '317031637'),\r\n" + 
						"	( 20170303, 'Psylent Hill', 8, 1, 3, 36, 2, 'tZmKKwf6s4A', '0gPIvviuzZwIsfC7q1uPTL', '309565137'),\r\n" + 
						"	( 20170531, 'Black Mamba', 22, 1, 3, 58, 2, '8YXKgZ393Ec', '4RZ2X0mHydwLXqKRppKDoO', '321536754'),\r\n" + 
						"	( 20170203, '2017 (Original Mix)', 24, 1, 3, 32, 2, 'IMfzttdGRJY', '4xgLavKbAFEFsQKCN76xjz', 'null'),\r\n" + 
						"	( 20170222, 'Psystyle ft. MC D', 23, 1, 3, 14, 2, 'cV5mg0sKhjA', '2cV3GfhwIjs7LN3YsZr6kf', '309011540'),\r\n" + 
						"	( 20170412, 'FTP (E-Force Remix)', 26, 1, 3, 59, 2, '9Fm7s6UMqj4', '6JzxMF1hAp0nlW2quMLKV5', '317324387'),\r\n" + 
						"	( 20170304, 'Sunlight (Stormerz Psy Edit)', 27, 1, 3, 19, 2, '7t9_WdifwjI', 'null', '299486470'),\r\n" + 
						"	( 20150825, 'T.M.M.O.', 28, 1, 4, 12, 2, 'iHURHc8h6Uk', '6WuOquatdi7Y3fn40cmgGb', '223102817'),\r\n" + 
						"	( 20150704, 'Live Your Life (Crystal Lakes Hard Edit)', 10, 1, 2, 19, 2, '5uHMn8KZf3Q', 'null', '213354900'),\r\n" + 
						"	( 20190622, 'The Edge Of The World', 30, 1, 7, 60, 2, 'kYzEvHvhZYk', '1CFv4FqqeaZkNViwWSdk47', '640481067'),\r\n" + 
						"	( 20180727, 'Chosen Ones', 29, 1, 2, 20, 2, 'J8DBq-5EIqE', '5B68sCt7ZmqOXLsLw4FB3G', '482778786'),\r\n" + 
						"	( 20160705, 'Encore', 2, 1, 4, 15, 2, 'lesUnDTrXsU', '7mTZA7oyEJemjtuW6dztML', 'null'),\r\n" + 
						"	( 20150401, 'Lies or Truth', 32, 1, 4, 14, 2, 'ns2o29nNr4I', '7lQJrNMwkgrK6Ji4yvFApt', '228050778'),\r\n" + 
						"	( 20161128, 'Make the Crowd Move', 2, 1, 4, 62, 2, 'x7zQg2hyBOM', '78WISIodZVTkd7vMhkImKh', '295174042'),\r\n" + 
						"	( 20190510, 'Miracles', 34, 1, 4, 20, 2, 'X6LbwjYgxeE', '4PRcBo2DknpGIwxWst0j4B', '697807321'),\r\n" + 
						"	( 20180622, 'Shine A Light', 35, 1, 4, 63, 2, 'Lk-yBArA9fg', '1ml3DHDWKMYaAjWy1NI5n2', '763160392'),\r\n" + 
						"	( 20160820, '#TWEEKAY16', 36, 1, 4, 20, 2, 'hnoa_heRbq8', '4hwWDcHiCDM4L2TicvpuAU', 'null'),\r\n" + 
						"	( 20150126, '#Tweekay14', 36, 1, 4, 20, 2, 'TMNfy9ffKXE', '6IJHbMSkcuXlZvgErrWYsF', '152316976'),\r\n" + 
						"	( 20160513, '152 Beatz', 37, 1, 2, 64, 1, '9mfjq7He9Dg', '70rYh8RcfJyRwSpN4nWgjL', '263951046'),\r\n" + 
						"	( 20120904, '150 Beatz', 37, 1, 1, 64, 1, 'Tnd2BhJW_PQ', '6HAIXXBBt1RkE6kCzkEYQh', '109189336'),\r\n" + 
						"	( 20180527, 'A Little Longer (Serzo Bootleg)', 38, 1, 6, 19, 2, 'uafOjD_JYdk', 'null', '449348928'),\r\n" + 
						"	( 20141218, 'A Million Stars', 40, 1, 4, 19, 2, 'ZFnRAcCh8Io', 'null', '182106386'),\r\n" + 
						"	( 20190517, 'Tomorrow ', 41, 1, 6, 3, 2, 'mtI0LNxkpQs', '6qmOIiLBVOEYXbRFcradtW', '621481911'),\r\n" + 
						"	( 20151123, 'Alchemy Of Hardstyle ', 7, 1, 4, 3, 2, 'r23_Fag6-KQ', '3iPs7Yut6eomY6Ufot3wGd', '234306126'),\r\n" + 
						"	( 20160919, 'Alive', 43, 1, 4, 66, 2, 'YTrKl7O35vk', '19GSfKmQdN83HOpXYoL6OT', '284099868'),\r\n" + 
						"	( 20080818, 'All of Me', 42, 1, 2, 38, 2, 'QeeRhL75DJQ', '78qFb3Jom9UIn7ijQfyHJF', '16730138'),\r\n" + 
						"	( 20200611, 'Glow', 174, 1, 4, 20, 2, 'BRcgUs61emc', '6SUnncll8eSguOPipEceXo', '834849259'),\r\n" + 
						"	( 20190419, 'Almost Home', 44, 1, 6, 20, 2, 'fjaXbqpnDwE', '4JtqKtNCsPmhVyvkPlFisk', '586895403'),\r\n" + 
						"	( 20131001, 'Almost There', 9, 1, 4, 20, 2, 'fFusGYTy670', '0CoyDEA9bzkV5cTXdEo2tB', '115260435'),\r\n" + 
						"	( 20170725, 'Anahera (Euphorizer Bootleg)', 46, 1, 4, 19, 2, 'kRbJ1a6GISc', 'null', '449903733'),\r\n" + 
						"	( 20140321, 'Alive', 48, 1, 4, 67, 2, 'fnd9T7iwMC4', '4HjZ3SbMwO0zuldSRVPyJ1', '139745365'),\r\n" + 
						"	( 20160901, 'Another World', 34, 1, 4, 20, 2, 'NDbu_9nPplk', '380Wusb372g4qmmUV7OeiK', '697806547'),\r\n" + 
						"	( 20180823, 'The Final Mission', 15, 1, 5, 15, 2, 'N2RGlesRFKo', '3FftWi9LQqRU4zBJdp2a2Z', '488024733'),\r\n" + 
						"	( 20160405, 'Emptiness', 50, 1, 4, 19, 2, 'akengiWWJBo', 'null', '257467569'),\r\n" + 
						"	( 20131128, 'Bad Habit', 36, 1, 4, 20, 2, '-Ccll-KFo-E', '01Ipr1M440sbRnficeJKVS', '104658926'),\r\n" + 
						"	( 20190122, 'Because Of You', 36, 1, 4, 20, 2, 'jgj0qbMgxNc', '2FRBUkTwFHEcPw4VOZm8nr', '588830439'),\r\n" + 
						"	( 20161122, 'Beyond Earth', 52, 1, 4, 68, 2, 'xlHkoBSUgbY', '0yCBVWX3QvPA98D6iDB8xZ', '292944457'),\r\n" + 
						"	( 20161201, 'Black & White', 9, 1, 4, 20, 2, 'vexokidBkAk', '57SpcmuBvezGh9tcSPckcz', '330331565'),\r\n" + 
						"	( 20131024, 'Blinded (Extended Version)', 9, 1, 4, 20, 2, '5VCAUrWh7qQ', '1MaUmStWjCj1K4G5JFu462', '41190880'),\r\n" + 
						"	( 20170223, 'Blue (Team Blue Mix)', 36, 1, 4, 19, 2, '7dsUz81laY8', 'null', '309153155'),\r\n" + 
						"	( 20140101, 'Boulevard of Broken Dreams (Omyqron Bootleg)', 54, 1, 4, 19, 2, 'LnVIA1v3l0Y', 'null', '145886260'),\r\n" + 
						"	( 20161211, 'Breakdown ', 56, 1, 4, 19, 2, 'I3abHYZp-GU', '1z3TTynryyyBBN990njC0x', '297414062'),\r\n" + 
						"	( 20190129, 'Breathe Hardstyle', 44, 1, 6, 20, 2, 'sFUdAKxe34c', '1Z9GyrcUqnXBNly2ZLwgDw', '566609259'),\r\n" + 
						"	( 20151112, 'Breathing Earth', 44, 1, 4, 20, 2, 'wRfVq5Pbp6Q', '1cXl9HHxbX1uDmXBM34JiO', '330331080'),\r\n" + 
						"	( 20161203, 'Brightest Days', 9, 1, 4, 20, 2, 'fWLco3t7-qo', '7pn6K8vq1zwbCwmZA8qcHY', '330331065'),\r\n" + 
						"	( 20180620, 'Broken (Wildstylez Remix)', 57, 1, 4, 17, 2, 'HAAn2wwnkyE', '4hpZgrwZyn9ApqC3VrMg6J', '511159170'),\r\n" + 
						"	( 20181002, 'Burn Out (Rebind Bootleg)', 60, 1, 4, 19, 2, 'O-hW34sMEic', 'null', '508345377')");
		db.getConnection().createStatement().execute("DELETE FROM songs WHERE releasedate = 0");
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  styles");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE styles (\r\n"
						+ "  id int  NOT NULL,\r\n"
						+ "  name varchar(50) NOT NULL DEFAULT '',\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM styles");
		db.getConnection().createStatement().execute("INSERT INTO styles (id, name) VALUES\r\n" + 
				"(23, 'Acid'),\r\n" + 
				"	(19, 'Acoustic Guitar'),\r\n" + 
				"	(20, 'Acoustic Piano'),\r\n" + 
				"	(31, 'Afro'),\r\n" + 
				"	(40, 'Anthem'),\r\n" + 
				"	(8, 'Anti-Climax'),\r\n" + 
				"	(17, 'Atmospheric'),\r\n" + 
				"	(46, 'Banda '),\r\n" + 
				"	(51, 'Breakbeat'),\r\n" + 
				"	(58, 'Breakdowns'),\r\n" + 
				"	(54, 'Claps'),\r\n" + 
				"	(30, 'Deep'),\r\n" + 
				"	(33, 'Disco'),\r\n" + 
				"	(14, 'Distorted Kick'),\r\n" + 
				"	(24, 'Dream'),\r\n" + 
				"	(43, 'Drum Kick'),\r\n" + 
				"	(28, 'Dub'),\r\n" + 
				"	(18, 'Electric Guitar'),\r\n" + 
				"	(21, 'Electric Piano'),\r\n" + 
				"	(1, 'Electronic'),\r\n" + 
				"	(9, 'Euphoric-Kick'),\r\n" + 
				"	(34, 'Funky'),\r\n" + 
				"	(32, 'Future'),\r\n" + 
				"	(35, 'Ghetto'),\r\n" + 
				"	(22, 'Hard'),\r\n" + 
				"	(47, 'Horns'),\r\n" + 
				"	(3, 'Instrumental'),\r\n" + 
				"	(4, 'Intro'),\r\n" + 
				"	(57, 'Jump Kick'),\r\n" + 
				"	(52, 'Jungle'),\r\n" + 
				"	(36, 'Latin'),\r\n" + 
				"	(2, 'Melodic'),\r\n" + 
				"	(6, 'Middle Intro'),\r\n" + 
				"	(29, 'Minimal'),\r\n" + 
				"	(44, 'Monotonous'),\r\n" + 
				"	(41, 'Mystic'),\r\n" + 
				"	(55, 'Noise'),\r\n" + 
				"	(49, 'Norteno'),\r\n" + 
				"	(10, 'Nu-Kick'),\r\n" + 
				"	(7, 'Outro'),\r\n" + 
				"	(59, 'Percussions'),\r\n" + 
				"	(38, 'Progressive'),\r\n" + 
				"	(12, 'Psy Bass'),\r\n" + 
				"	(11, 'Raw-Kick'),\r\n" + 
				"	(13, 'Reverse Bass'),\r\n" + 
				"	(53, 'Snares'),\r\n" + 
				"	(37, 'Speed'),\r\n" + 
				"	(48, 'Strings'),\r\n" + 
				"	(45, 'Subbasses'),\r\n" + 
				"	(50, 'Synths'),\r\n" + 
				"	(27, 'Tech'),\r\n" + 
				"	(56, 'Transposed'),\r\n" + 
				"	(39, 'Tropical'),\r\n" + 
				"	(25, 'Uplifting'),\r\n" + 
				"	(26, 'Vocal'),\r\n" + 
				"	(42, 'Woodwinds')");
		db.getConnection().createStatement().execute("Delete from styles where name = 'deleted'");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE usertbl");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement().execute("CREATE TABLE usertbl (\r\n"
				+ "  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
				+ "  name varchar(50) NOT NULL DEFAULT '0',\r\n" 
				+ "  password varchar(256) NOT NULL DEFAULT '0',\r\n"
				+ "  email varchar(50) DEFAULT NULL,\r\n" 
				+ "  birthday int DEFAULT NULL,\r\n"
				+ "  usertype int  NOT NULL,\r\n" 
				+ "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM usertbl");
		db.getConnection().createStatement().execute("INSERT INTO usertbl (name, password, email, birthday, usertype) VALUES\r\n" + 
				"	('MoDC', '07a155e4aa1bfb7211705467ab93571ad5c568b2b810fa098f2004444f89fe74', 'ms@schneeweis-audio-video.de', 20000423, 1),\r\n" + 
				"	('Default', 'no', 'no', 20000423, 2),\r\n" + 
				"	('MoDC-Music', '9dec1d24dddca7a9f5b2656732f3346a2b06e895609941034c8566ee8493ee9f', 'lollollollo664@gmail.com', 20000423, 2),\r\n" + 
				"	('Test Admin', '6ad96f79231769fe49411272449928bb1db3ea2f4d09ee5e150894009a3199c7', 'admin@test.de', 19990101, 1),\r\n" + 
				"	('Test User', '6a1623807578f58aec5ab0ec1f540472740c2df0d65b6c755197120912499c23', 'user@test.com', 20000101, 2)");
		db.getConnection().createStatement().execute("DELETE FROM usertbl WHERE id = 2");
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE usertype");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE usertype (\r\n"
						+ "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
						+ "  name varchar(50) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM usertype");
		db.getConnection().createStatement()
				.execute("INSERT INTO usertype (name) VALUES\r\n" + 
						"	('Administrator'),\r\n" + 
						"	('Defaultuser'),\r\n"  + 
						"	('Moderator')," + 
						"	('Artist'),\r\n" + 						
						"	('Label')\r\n");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_artist_label");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_artist_label (\r\n" + "  artist int  NOT NULL DEFAULT 0,\r\n"
						+ "  label int  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (artist, label)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM z_artist_label");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_feeling_song");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_feeling_song (\r\n" + "  feeling int  NOT NULL,\r\n"
						+ "  song bigint  NOT NULL DEFAULT 0,\r\n" + "  upvoted int  NOT NULL DEFAULT 0,\r\n"
						+ "  downvoted int  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (feeling,song)\r\n" + ") ");
		db.getConnection().createStatement().execute("DELETE FROM z_feeling_song");
		db.getConnection().createStatement()
				.execute("INSERT INTO z_feeling_song (feeling, song, upvoted, downvoted) VALUES\r\n"
						+ "	(1, 1, 1, 0),\r\n" + 
						"	(1, 2, 1, 0),\r\n" + 
						"	(1, 3, 1, 0),\r\n" + 
						"	(2, 25, 1, 0),\r\n" + 
						"	(2, 40, 1, 0),\r\n" + 
						"	(2, 43, 1, 0),\r\n" + 
						"	(2, 51, 1, 0),\r\n" + 
						"	(2, 53, 1, 0),\r\n" + 
						"	(2, 54, 1, 0),\r\n" + 
						"	(2, 58, 1, 0),\r\n" + 
						"	(3, 1, 1, 0),\r\n" + 
						"	(3, 3, 1, 0),\r\n" + 
						"	(3, 28, 1, 0),\r\n" + 
						"	(3, 33, 1, 0),\r\n" + 
						"	(3, 37, 1, 0),\r\n" + 
						"	(3, 38, 1, 0),\r\n" + 
						"	(3, 40, 1, 0),\r\n" + 
						"	(3, 43, 1, 0),\r\n" + 
						"	(3, 48, 1, 0),\r\n" + 
						"	(3, 58, 1, 0),\r\n" + 
						"	(4, 2, 1, 0),\r\n" + 
						"	(4, 3, 1, 0),\r\n" + 
						"	(4, 8, 1, 0),\r\n" + 
						"	(4, 12, 1, 0),\r\n" + 
						"	(4, 21, 1, 0),\r\n" + 
						"	(4, 22, 1, 0),\r\n" + 
						"	(4, 23, 1, 0),\r\n" + 
						"	(4, 24, 1, 0),\r\n" + 
						"	(4, 25, 1, 0),\r\n" + 
						"	(4, 26, 1, 0),\r\n" + 
						"	(4, 27, 1, 0),\r\n" + 
						"	(4, 28, 1, 0),\r\n" + 
						"	(4, 33, 1, 0),\r\n" + 
						"	(4, 34, 1, 0),\r\n" + 
						"	(4, 36, 1, 0),\r\n" + 
						"	(4, 39, 1, 0),\r\n" + 
						"	(4, 40, 1, 0),\r\n" + 
						"	(4, 42, 1, 0),\r\n" + 
						"	(4, 43, 1, 0),\r\n" + 
						"	(4, 44, 1, 0),\r\n" + 
						"	(4, 45, 1, 0),\r\n" + 
						"	(4, 46, 1, 0),\r\n" + 
						"	(4, 49, 1, 0),\r\n" + 
						"	(4, 50, 1, 0),\r\n" + 
						"	(4, 53, 1, 0),\r\n" + 
						"	(4, 55, 1, 0),\r\n" + 
						"	(4, 56, 1, 0),\r\n" + 
						"	(4, 57, 1, 0),\r\n" + 
						"	(4, 59, 1, 0),\r\n" + 
						"	(5, 1, 1, 0),\r\n" + 
						"	(5, 2, 1, 0),\r\n" + 
						"	(5, 3, 1, 0),\r\n" + 
						"	(5, 9, 1, 0),\r\n" + 
						"	(5, 10, 1, 0),\r\n" + 
						"	(5, 11, 1, 0),\r\n" + 
						"	(5, 12, 1, 0),\r\n" + 
						"	(5, 18, 1, 0),\r\n" + 
						"	(5, 19, 1, 0),\r\n" + 
						"	(5, 21, 1, 0),\r\n" + 
						"	(5, 26, 1, 0),\r\n" + 
						"	(5, 27, 1, 0),\r\n" + 
						"	(5, 28, 1, 0),\r\n" + 
						"	(5, 29, 1, 0),\r\n" + 
						"	(5, 30, 1, 0),\r\n" + 
						"	(5, 31, 1, 0),\r\n" + 
						"	(5, 32, 1, 0),\r\n" + 
						"	(5, 33, 1, 0),\r\n" + 
						"	(5, 37, 1, 0),\r\n" + 
						"	(5, 39, 1, 0),\r\n" + 
						"	(5, 40, 1, 0),\r\n" + 
						"	(5, 43, 1, 0),\r\n" + 
						"	(5, 44, 1, 0),\r\n" + 
						"	(5, 45, 1, 0),\r\n" + 
						"	(5, 47, 1, 0),\r\n" + 
						"	(5, 48, 1, 0),\r\n" + 
						"	(5, 52, 1, 0),\r\n" + 
						"	(5, 53, 1, 0),\r\n" + 
						"	(5, 55, 1, 0),\r\n" + 
						"	(5, 59, 1, 0),\r\n" + 
						"	(6, 12, 1, 0),\r\n" + 
						"	(7, 10, 1, 0),\r\n" + 
						"	(7, 13, 1, 0),\r\n" + 
						"	(7, 21, 1, 0),\r\n" + 
						"	(7, 26, 1, 0),\r\n" + 
						"	(8, 8, 1, 0),\r\n" + 
						"	(8, 21, 1, 0),\r\n" + 
						"	(8, 23, 1, 0),\r\n" + 
						"	(8, 26, 1, 0),\r\n" + 
						"	(8, 27, 1, 0),\r\n" + 
						"	(8, 28, 1, 0),\r\n" + 
						"	(8, 35, 1, 0),\r\n" + 
						"	(8, 39, 1, 0),\r\n" + 
						"	(8, 40, 1, 0),\r\n" + 
						"	(8, 43, 1, 0),\r\n" + 
						"	(8, 54, 1, 0),\r\n" + 
						"	(8, 57, 1, 0),\r\n" + 
						"	(10, 1, 1, 0),\r\n" + 
						"	(10, 8, 1, 0),\r\n" + 
						"	(10, 9, 1, 0),\r\n" + 
						"	(10, 10, 1, 0),\r\n" + 
						"	(10, 11, 1, 0),\r\n" + 
						"	(10, 13, 1, 0),\r\n" + 
						"	(10, 14, 1, 0),\r\n" + 
						"	(10, 15, 1, 0),\r\n" + 
						"	(10, 18, 1, 0),\r\n" + 
						"	(10, 19, 1, 0),\r\n" + 
						"	(10, 20, 1, 0),\r\n" + 
						"	(10, 21, 1, 0),\r\n" + 
						"	(10, 23, 1, 0),\r\n" + 
						"	(10, 24, 1, 0),\r\n" + 
						"	(10, 25, 1, 0),\r\n" + 
						"	(10, 26, 1, 0),\r\n" + 
						"	(10, 27, 1, 0),\r\n" + 
						"	(10, 28, 1, 0),\r\n" + 
						"	(10, 29, 1, 0),\r\n" + 
						"	(10, 30, 1, 0),\r\n" + 
						"	(10, 33, 1, 0),\r\n" + 
						"	(10, 34, 1, 0),\r\n" + 
						"	(10, 35, 1, 0),\r\n" + 
						"	(10, 36, 1, 0),\r\n" + 
						"	(10, 37, 1, 0),\r\n" + 
						"	(10, 38, 1, 0),\r\n" + 
						"	(10, 39, 1, 0),\r\n" + 
						"	(10, 40, 1, 0),\r\n" + 
						"	(10, 41, 1, 0),\r\n" + 
						"	(10, 42, 1, 0),\r\n" + 
						"	(10, 43, 1, 0),\r\n" + 
						"	(10, 44, 1, 0),\r\n" + 
						"	(10, 45, 1, 0),\r\n" + 
						"	(10, 46, 1, 0),\r\n" + 
						"	(10, 47, 1, 0),\r\n" + 
						"	(10, 48, 1, 0),\r\n" + 
						"	(10, 50, 1, 0),\r\n" + 
						"	(10, 51, 1, 0),\r\n" + 
						"	(10, 52, 1, 0),\r\n" + 
						"	(10, 53, 1, 0),\r\n" + 
						"	(10, 55, 1, 0),\r\n" + 
						"	(10, 56, 1, 0),\r\n" + 
						"	(10, 58, 1, 0),\r\n" + 
						"	(10, 59, 1, 0),\r\n" + 
						"	(11, 31, 1, 0),\r\n" + 
						"	(11, 32, 1, 0),\r\n" + 
						"	(11, 47, 1, 0),\r\n" + 
						"	(11, 52, 1, 0),\r\n" + 
						"	(12, 51, 1, 0),\r\n" + 
						"	(13, 40, 1, 0),\r\n" + 
						"	(13, 57, 1, 0),\r\n" + 
						"	(14, 2, 1, 0),\r\n" + 
						"	(14, 8, 1, 0),\r\n" + 
						"	(14, 21, 1, 0),\r\n" + 
						"	(14, 23, 1, 0),\r\n" + 
						"	(14, 28, 1, 0),\r\n" + 
						"	(14, 39, 1, 0),\r\n" + 
						"	(14, 40, 1, 0),\r\n" + 
						"	(14, 50, 1, 0),\r\n" + 
						"	(14, 54, 1, 0),\r\n" + 
						"	(14, 59, 1, 0),\r\n" + 
						"	(15, 20, 1, 0),\r\n" + 
						"	(15, 50, 1, 0),\r\n" + 
						"	(15, 51, 1, 0),\r\n" + 
						"	(15, 53, 1, 0),\r\n" + 
						"	(15, 54, 1, 0),\r\n" + 
						"	(16, 1, 1, 0),\r\n" + 
						"	(16, 3, 1, 0),\r\n" + 
						"	(16, 8, 1, 0),\r\n" + 
						"	(16, 20, 1, 0),\r\n" + 
						"	(16, 21, 1, 0),\r\n" + 
						"	(16, 23, 1, 0),\r\n" + 
						"	(16, 26, 1, 0),\r\n" + 
						"	(16, 27, 1, 0),\r\n" + 
						"	(16, 28, 1, 0),\r\n" + 
						"	(16, 35, 1, 0),\r\n" + 
						"	(16, 37, 1, 0),\r\n" + 
						"	(16, 39, 1, 0),\r\n" + 
						"	(16, 40, 1, 0),\r\n" + 
						"	(16, 43, 1, 0),\r\n" + 
						"	(16, 50, 1, 0),\r\n" + 
						"	(16, 57, 1, 0),\r\n" + 
						"	(17, 25, 1, 0),\r\n" + 
						"	(17, 33, 1, 0),\r\n" + 
						"	(17, 38, 1, 0),\r\n" + 
						"	(17, 39, 1, 0),\r\n" + 
						"	(17, 43, 1, 0),\r\n" + 
						"	(17, 51, 1, 0),\r\n" + 
						"	(17, 53, 1, 0),\r\n" + 
						"	(17, 58, 1, 0),\r\n" + 
						"	(18, 9, 1, 0),\r\n" + 
						"	(19, 14, 1, 0),\r\n" + 
						"	(19, 20, 1, 0),\r\n" + 
						"	(19, 42, 1, 0),\r\n" + 
						"	(19, 44, 1, 0),\r\n" + 
						"	(19, 46, 1, 0),\r\n" + 
						"	(19, 50, 1, 0),\r\n" + 
						"	(19, 51, 1, 0),\r\n" + 
						"	(19, 53, 1, 0),\r\n" + 
						"	(19, 54, 1, 0),\r\n" + 
						"	(19, 58, 1, 0),\r\n" + 
						"	(20, 25, 1, 0),\r\n" + 
						"	(20, 33, 1, 0),\r\n" + 
						"	(20, 51, 1, 0),\r\n" + 
						"	(20, 53, 1, 0),\r\n" + 
						"	(20, 58, 1, 0),\r\n" + 
						"	(21, 11, 1, 0),\r\n" + 
						"	(21, 16, 1, 0),\r\n" + 
						"	(21, 17, 1, 0),\r\n" + 
						"	(21, 22, 1, 0),\r\n" + 
						"	(21, 26, 1, 0),\r\n" + 
						"	(21, 29, 1, 0),\r\n" + 
						"	(21, 30, 1, 0),\r\n" + 
						"	(21, 38, 1, 0),\r\n" + 
						"	(21, 41, 1, 0),\r\n" + 
						"	(21, 46, 1, 0),\r\n" + 
						"	(21, 48, 1, 0),\r\n" + 
						"	(21, 50, 1, 0),\r\n" + 
						"	(21, 53, 1, 0),\r\n" + 
						"	(21, 57, 1, 0),\r\n" + 
						"	(21, 59, 1, 0),\r\n" + 
						"	(23, 23, 1, 0),\r\n" + 
						"	(23, 29, 1, 0),\r\n" + 
						"	(23, 30, 1, 0),\r\n" + 
						"	(23, 31, 1, 0),\r\n" + 
						"	(23, 47, 1, 0),\r\n" + 
						"	(23, 52, 1, 0),\r\n" + 
						"	(24, 2, 1, 0),\r\n" + 
						"	(24, 12, 1, 0),\r\n" + 
						"	(24, 21, 1, 0),\r\n" + 
						"	(24, 23, 1, 0),\r\n" + 
						"	(24, 26, 1, 0),\r\n" + 
						"	(24, 29, 1, 0),\r\n" + 
						"	(24, 31, 1, 0),\r\n" + 
						"	(24, 33, 1, 0),\r\n" + 
						"	(24, 44, 1, 0),\r\n" + 
						"	(24, 45, 1, 0),\r\n" + 
						"	(24, 47, 1, 0),\r\n" + 
						"	(24, 48, 1, 0),\r\n" + 
						"	(24, 52, 1, 0),\r\n" + 
						"	(24, 59, 1, 0),\r\n" + 
						"	(25, 13, 1, 0),\r\n" + 
						"	(25, 15, 1, 0),\r\n" + 
						"	(25, 16, 1, 0),\r\n" + 
						"	(25, 17, 1, 0),\r\n" + 
						"	(25, 19, 1, 0),\r\n" + 
						"	(25, 22, 1, 0),\r\n" + 
						"	(25, 24, 1, 0),\r\n" + 
						"	(25, 32, 1, 0),\r\n" + 
						"	(25, 45, 1, 0),\r\n" + 
						"	(25, 47, 1, 0),\r\n" + 
						"	(25, 49, 1, 0),\r\n" + 
						"	(25, 56, 1, 0),\r\n" + 
						"	(26, 10, 1, 0),\r\n" + 
						"	(26, 14, 1, 0),\r\n" + 
						"	(26, 22, 1, 0),\r\n" + 
						"	(26, 24, 1, 0),\r\n" + 
						"	(26, 34, 1, 0),\r\n" + 
						"	(26, 35, 1, 0),\r\n" + 
						"	(26, 36, 1, 0),\r\n" + 
						"	(26, 37, 1, 0),\r\n" + 
						"	(26, 39, 1, 0),\r\n" + 
						"	(26, 40, 1, 0),\r\n" + 
						"	(26, 41, 1, 0),\r\n" + 
						"	(26, 42, 1, 0),\r\n" + 
						"	(26, 43, 1, 0),\r\n" + 
						"	(26, 44, 1, 0),\r\n" + 
						"	(26, 46, 1, 0),\r\n" + 
						"	(26, 49, 1, 0),\r\n" + 
						"	(26, 50, 1, 0),\r\n" + 
						"	(26, 51, 1, 0),\r\n" + 
						"	(26, 53, 1, 0),\r\n" + 
						"	(26, 54, 1, 0),\r\n" + 
						"	(26, 55, 1, 0),\r\n" + 
						"	(26, 56, 1, 0),\r\n" + 
						"	(26, 57, 1, 0),\r\n" + 
						"	(27, 29, 1, 0),\r\n" + 
						"	(27, 30, 1, 0),\r\n" + 
						"	(27, 31, 1, 0),\r\n" + 
						"	(27, 34, 1, 0),\r\n" + 
						"	(27, 44, 1, 0),\r\n" + 
						"	(27, 46, 1, 0),\r\n" + 
						"	(27, 48, 1, 0),\r\n" + 
						"	(27, 52, 1, 0),\r\n" + 
						"	(27, 57, 1, 0),\r\n" + 
						"	(27, 59, 1, 0),\r\n" + 
						"	(28, 9, 1, 0),\r\n" + 
						"	(28, 18, 1, 0),\r\n" + 
						"	(29, 24, 1, 0),\r\n" + 
						"	(29, 42, 1, 0),\r\n" + 
						"	(29, 44, 1, 0),\r\n" + 
						"	(29, 46, 1, 0),\r\n" + 
						"	(29, 49, 1, 0),\r\n" + 
						"	(29, 50, 1, 0),\r\n" + 
						"	(29, 54, 1, 0),\r\n" + 
						"	(29, 55, 1, 0),\r\n" + 
						"	(29, 56, 1, 0),\r\n" + 
						"	(29, 57, 1, 0),\r\n" + 
						"	(30, 36, 1, 0),\r\n" + 
						"	(30, 41, 1, 0),\r\n" + 
						"	(30, 42, 1, 0),\r\n" + 
						"	(30, 45, 1, 0),\r\n" + 
						"	(30, 46, 1, 0),\r\n" + 
						"	(30, 47, 1, 0),\r\n" + 
						"	(30, 49, 1, 0),\r\n" + 
						"	(30, 50, 1, 0),\r\n" + 
						"	(30, 51, 1, 0),\r\n" + 
						"	(30, 52, 1, 0),\r\n" + 
						"	(30, 53, 1, 0),\r\n" + 
						"	(30, 55, 1, 0),\r\n" + 
						"	(30, 56, 1, 0)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_genre_style");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
				throw e;
			}
		}
		db.getConnection().createStatement().execute("CREATE TABLE z_genre_style (\r\n" + "  genre int  NOT NULL,\r\n"
				+ "  style int  NOT NULL,\r\n" + "  PRIMARY KEY (genre,style)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM z_genre_style");
		db.getConnection().createStatement()
				.execute("INSERT INTO z_genre_style (genre, style) VALUES\r\n" + "	(1, 1),\r\n" + 
						"	(1, 50),\r\n" + 
						"	(2, 1),\r\n" + 
						"	(2, 14),\r\n" + 
						"	(2, 22),\r\n" + 
						"	(2, 50),\r\n" + 
						"	(3, 1),\r\n" + 
						"	(3, 12),\r\n" + 
						"	(3, 50),\r\n" + 
						"	(4, 1),\r\n" + 
						"	(4, 2),\r\n" + 
						"	(4, 9),\r\n" + 
						"	(4, 14),\r\n" + 
						"	(4, 50),\r\n" + 
						"	(5, 1),\r\n" + 
						"	(5, 11),\r\n" + 
						"	(5, 14),\r\n" + 
						"	(5, 50),\r\n" + 
						"	(6, 1),\r\n" + 
						"	(6, 2),\r\n" + 
						"	(6, 11),\r\n" + 
						"	(6, 14),\r\n" + 
						"	(6, 50),\r\n" + 
						"	(7, 1),\r\n" + 
						"	(7, 14),\r\n" + 
						"	(7, 22),\r\n" + 
						"	(7, 50),\r\n" + 
						"	(8, 1),\r\n" + 
						"	(8, 14),\r\n" + 
						"	(8, 22),\r\n" + 
						"	(8, 50),\r\n" + 
						"	(9, 1),\r\n" + 
						"	(9, 22),\r\n" + 
						"	(9, 28),\r\n" + 
						"	(9, 50),\r\n" + 
						"	(10, 1),\r\n" + 
						"	(10, 14),\r\n" + 
						"	(10, 22),\r\n" + 
						"	(10, 50),\r\n" + 
						"	(11, 26),\r\n" + 
						"	(12, 1),\r\n" + 
						"	(13, 1),\r\n" + 
						"	(13, 30),\r\n" + 
						"	(14, 1),\r\n" + 
						"	(14, 17),\r\n" + 
						"	(14, 50),\r\n" + 
						"	(115, 1),\r\n" + 
						"	(115, 27),\r\n" + 
						"	(115, 43),\r\n" + 
						"	(115, 50),\r\n" + 
						"	(116, 1),\r\n" + 
						"	(116, 28),\r\n" + 
						"	(116, 50),\r\n" + 
						"	(117, 1),\r\n" + 
						"	(117, 43),\r\n" + 
						"	(117, 50),\r\n" + 
						"	(117, 53),\r\n" + 
						"	(117, 54),\r\n" + 
						"	(118, 1),\r\n" + 
						"	(118, 29),\r\n" + 
						"	(118, 51),\r\n" + 
						"	(118, 52),\r\n" + 
						"	(119, 1),\r\n" + 
						"	(119, 22),\r\n" + 
						"	(119, 30),\r\n" + 
						"	(120, 1),\r\n" + 
						"	(120, 2),\r\n" + 
						"	(120, 17),\r\n" + 
						"	(120, 24),\r\n" + 
						"	(120, 25),\r\n" + 
						"	(120, 38),\r\n" + 
						"	(121, 1),\r\n" + 
						"	(121, 3),\r\n" + 
						"	(121, 23),\r\n" + 
						"	(121, 50),\r\n" + 
						"	(122, 1),\r\n" + 
						"	(122, 22),\r\n" + 
						"	(123, 1),\r\n" + 
						"	(123, 2),\r\n" + 
						"	(123, 3),\r\n" + 
						"	(123, 17),\r\n" + 
						"	(123, 25),\r\n" + 
						"	(124, 1),\r\n" + 
						"	(124, 2),\r\n" + 
						"	(124, 26),\r\n" + 
						"	(124, 38),\r\n" + 
						"	(125, 1),\r\n" + 
						"	(125, 12),\r\n" + 
						"	(126, 1),\r\n" + 
						"	(126, 25),\r\n" + 
						"	(126, 27),\r\n" + 
						"	(126, 43),\r\n" + 
						"	(127, 1),\r\n" + 
						"	(127, 27),\r\n" + 
						"	(127, 29),\r\n" + 
						"	(127, 38),\r\n" + 
						"	(127, 43),\r\n" + 
						"	(127, 44),\r\n" + 
						"	(128, 1),\r\n" + 
						"	(128, 23),\r\n" + 
						"	(128, 27),\r\n" + 
						"	(128, 43),\r\n" + 
						"	(128, 50),\r\n" + 
						"	(130, 1),\r\n" + 
						"	(130, 27),\r\n" + 
						"	(130, 35),\r\n" + 
						"	(130, 45),\r\n" + 
						"	(131, 1),\r\n" + 
						"	(131, 19),\r\n" + 
						"	(131, 22),\r\n" + 
						"	(131, 46),\r\n" + 
						"	(131, 49),\r\n" + 
						"	(133, 1),\r\n" + 
						"	(133, 26),\r\n" + 
						"	(133, 33),\r\n" + 
						"	(133, 38),\r\n" + 
						"	(133, 50),\r\n" + 
						"	(134, 1),\r\n" + 
						"	(134, 22),\r\n" + 
						"	(134, 27),\r\n" + 
						"	(134, 50),\r\n" + 
						"	(134, 51),\r\n" + 
						"	(134, 52),\r\n" + 
						"	(135, 1),\r\n" + 
						"	(135, 14),\r\n" + 
						"	(135, 22),\r\n" + 
						"	(135, 27),\r\n" + 
						"	(135, 55),\r\n" + 
						"	(136, 1),\r\n" + 
						"	(136, 22),\r\n" + 
						"	(136, 43),\r\n" + 
						"	(136, 51),\r\n" + 
						"	(136, 55),\r\n" + 
						"	(138, 1),\r\n" + 
						"	(138, 14),\r\n" + 
						"	(138, 22),\r\n" + 
						"	(138, 29),\r\n" + 
						"	(138, 50),\r\n" + 
						"	(139, 1),\r\n" + 
						"	(139, 14),\r\n" + 
						"	(139, 22),\r\n" + 
						"	(139, 27),\r\n" + 
						"	(139, 50),\r\n" + 
						"	(140, 1),\r\n" + 
						"	(140, 21),\r\n" + 
						"	(140, 22),\r\n" + 
						"	(140, 50),\r\n" + 
						"	(140, 51),\r\n" + 
						"	(140, 56),\r\n" + 
						"	(141, 1),\r\n" + 
						"	(141, 14),\r\n" + 
						"	(141, 22),\r\n" + 
						"	(141, 27),\r\n" + 
						"	(141, 37),\r\n" + 
						"	(141, 50),\r\n" + 
						"	(141, 55),\r\n" + 
						"	(145, 1),\r\n" + 
						"	(145, 27),\r\n" + 
						"	(145, 29),\r\n" + 
						"	(145, 43),\r\n" + 
						"	(145, 44),\r\n" + 
						"	(146, 1),\r\n" + 
						"	(146, 27),\r\n" + 
						"	(146, 28),\r\n" + 
						"	(147, 1),\r\n" + 
						"	(147, 2),\r\n" + 
						"	(147, 27),\r\n" + 
						"	(147, 56),\r\n" + 
						"	(148, 1),\r\n" + 
						"	(148, 14),\r\n" + 
						"	(148, 27),\r\n" + 
						"	(148, 57),\r\n" + 
						"	(151, 1),\r\n" + 
						"	(151, 2),\r\n" + 
						"	(151, 28),\r\n" + 
						"	(152, 1),\r\n" + 
						"	(152, 28),\r\n" + 
						"	(152, 55),\r\n" + 
						"	(153, 1),\r\n" + 
						"	(153, 27),\r\n" + 
						"	(153, 43),\r\n" + 
						"	(153, 50),\r\n" + 
						"	(153, 53),\r\n" + 
						"	(153, 54),\r\n" + 
						"	(154, 1),\r\n" + 
						"	(154, 30),\r\n" + 
						"	(154, 43),\r\n" + 
						"	(154, 50),\r\n" + 
						"	(154, 53),\r\n" + 
						"	(154, 54),\r\n" + 
						"	(155, 1),\r\n" + 
						"	(155, 23),\r\n" + 
						"	(155, 27),\r\n" + 
						"	(155, 43),\r\n" + 
						"	(155, 50),\r\n" + 
						"	(156, 1),\r\n" + 
						"	(156, 2),\r\n" + 
						"	(156, 50),\r\n" + 
						"	(156, 58),\r\n" + 
						"	(156, 59)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE z_playlist_songs");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_playlist_songs (\r\n" + "  playlist int  NOT NULL,\r\n"
						+ "  song bigint  NOT NULL DEFAULT 0,\r\n" + "  ordered int NOT NULL,\r\n"
						+ "  PRIMARY KEY (playlist,song)\r\n" + ") ");
		db.getConnection().createStatement().execute("DELETE FROM z_playlist_songs");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_song_co_artist");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_song_co_artist (\r\n" + "  song_id bigint  NOT NULL,\r\n"
						+ "  co_artist_id int  NOT NULL,\r\n" + "  PRIMARY KEY (song_id,co_artist_id)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM z_song_co_artist");
		db.getConnection().createStatement().execute("INSERT INTO z_song_co_artist (song_id, co_artist_id) VALUES\r\n"
				+ "	(1, 3),\r\n" + 
				"	(1, 4),\r\n" + 
				"	(2, 14),\r\n" + 
				"	(3, 13),\r\n" + 
				"	(9, 18),\r\n" + 
				"	(11, 2),\r\n" + 
				"	(11, 20),\r\n" + 
				"	(18, 25),\r\n" + 
				"	(20, 9),\r\n" + 
				"	(21, 29),\r\n" + 
				"	(22, 31),\r\n" + 
				"	(25, 2),\r\n" + 
				"	(26, 33),\r\n" + 
				"	(28, 2),\r\n" + 
				"	(28, 13),\r\n" + 
				"	(33, 39),\r\n" + 
				"	(35, 210),\r\n" + 
				"	(42, 96),\r\n" + 
				"	(44, 49),\r\n" + 
				"	(46, 40),\r\n" + 
				"	(47, 51),\r\n" + 
				"	(48, 34),\r\n" + 
				"	(52, 19),\r\n" + 
				"	(52, 41),\r\n" + 
				"	(53, 55),\r\n" + 
				"	(55, 20),\r\n" + 
				"	(58, 2),\r\n" + 
				"	(58, 58),\r\n" + 
				"	(58, 59),\r\n" + 
				"	(59, 61),\r\n" + 
				"	(59, 62)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_song_style");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
			}
		}
		db.getConnection().createStatement().execute("CREATE TABLE z_song_style (\r\n" + "  song bigint  NOT NULL,\r\n"
				+ "  style int  NOT NULL,\r\n" + "  PRIMARY KEY (song,style)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM z_song_style");
		db.getConnection().createStatement()
				.execute("INSERT INTO z_song_style (song, style) VALUES\r\n" + "	(1, 1),\r\n" + 
						"	(1, 2),\r\n" + 
						"	(1, 14),\r\n" + 
						"	(2, 1),\r\n" + 
						"	(2, 2),\r\n" + 
						"	(2, 9),\r\n" + 
						"	(2, 14),\r\n" + 
						"	(3, 1),\r\n" + 
						"	(3, 2),\r\n" + 
						"	(3, 9),\r\n" + 
						"	(3, 14),\r\n" + 
						"	(8, 1),\r\n" + 
						"	(8, 2),\r\n" + 
						"	(8, 11),\r\n" + 
						"	(8, 14),\r\n" + 
						"	(8, 17),\r\n" + 
						"	(9, 1),\r\n" + 
						"	(9, 11),\r\n" + 
						"	(9, 12),\r\n" + 
						"	(10, 1),\r\n" + 
						"	(10, 4),\r\n" + 
						"	(10, 12),\r\n" + 
						"	(11, 1),\r\n" + 
						"	(11, 2),\r\n" + 
						"	(11, 4),\r\n" + 
						"	(11, 6),\r\n" + 
						"	(11, 7),\r\n" + 
						"	(11, 10),\r\n" + 
						"	(11, 12),\r\n" + 
						"	(12, 1),\r\n" + 
						"	(12, 2),\r\n" + 
						"	(12, 11),\r\n" + 
						"	(12, 12),\r\n" + 
						"	(12, 14),\r\n" + 
						"	(13, 1),\r\n" + 
						"	(13, 12),\r\n" + 
						"	(14, 1),\r\n" + 
						"	(14, 3),\r\n" + 
						"	(14, 4),\r\n" + 
						"	(14, 7),\r\n" + 
						"	(14, 12),\r\n" + 
						"	(15, 1),\r\n" + 
						"	(15, 3),\r\n" + 
						"	(15, 12),\r\n" + 
						"	(16, 1),\r\n" + 
						"	(16, 4),\r\n" + 
						"	(16, 7),\r\n" + 
						"	(16, 12),\r\n" + 
						"	(17, 1),\r\n" + 
						"	(17, 12),\r\n" + 
						"	(18, 1),\r\n" + 
						"	(18, 11),\r\n" + 
						"	(18, 12),\r\n" + 
						"	(19, 1),\r\n" + 
						"	(19, 12),\r\n" + 
						"	(20, 1),\r\n" + 
						"	(20, 2),\r\n" + 
						"	(20, 4),\r\n" + 
						"	(20, 6),\r\n" + 
						"	(20, 7),\r\n" + 
						"	(20, 9),\r\n" + 
						"	(20, 14),\r\n" + 
						"	(21, 1),\r\n" + 
						"	(21, 9),\r\n" + 
						"	(21, 14),\r\n" + 
						"	(21, 18),\r\n" + 
						"	(22, 2),\r\n" + 
						"	(22, 7),\r\n" + 
						"	(22, 14),\r\n" + 
						"	(23, 1),\r\n" + 
						"	(23, 2),\r\n" + 
						"	(23, 9),\r\n" + 
						"	(23, 10),\r\n" + 
						"	(23, 14),\r\n" + 
						"	(23, 18),\r\n" + 
						"	(24, 1),\r\n" + 
						"	(24, 2),\r\n" + 
						"	(24, 3),\r\n" + 
						"	(24, 6),\r\n" + 
						"	(24, 9),\r\n" + 
						"	(24, 14),\r\n" + 
						"	(24, 20),\r\n" + 
						"	(25, 1),\r\n" + 
						"	(25, 2),\r\n" + 
						"	(25, 9),\r\n" + 
						"	(25, 14),\r\n" + 
						"	(26, 1),\r\n" + 
						"	(26, 2),\r\n" + 
						"	(26, 4),\r\n" + 
						"	(26, 8),\r\n" + 
						"	(26, 9),\r\n" + 
						"	(26, 14),\r\n" + 
						"	(27, 1),\r\n" + 
						"	(27, 2),\r\n" + 
						"	(27, 9),\r\n" + 
						"	(27, 14),\r\n" + 
						"	(28, 1),\r\n" + 
						"	(28, 2),\r\n" + 
						"	(28, 9),\r\n" + 
						"	(28, 14),\r\n" + 
						"	(28, 20),\r\n" + 
						"	(29, 1),\r\n" + 
						"	(29, 2),\r\n" + 
						"	(29, 6),\r\n" + 
						"	(29, 9),\r\n" + 
						"	(29, 13),\r\n" + 
						"	(29, 14),\r\n" + 
						"	(30, 1),\r\n" + 
						"	(30, 2),\r\n" + 
						"	(30, 4),\r\n" + 
						"	(30, 6),\r\n" + 
						"	(30, 7),\r\n" + 
						"	(30, 9),\r\n" + 
						"	(30, 13),\r\n" + 
						"	(30, 14),\r\n" + 
						"	(31, 1),\r\n" + 
						"	(31, 13),\r\n" + 
						"	(31, 14),\r\n" + 
						"	(32, 1),\r\n" + 
						"	(32, 2),\r\n" + 
						"	(32, 14),\r\n" + 
						"	(32, 26),\r\n" + 
						"	(33, 1),\r\n" + 
						"	(33, 2),\r\n" + 
						"	(33, 11),\r\n" + 
						"	(33, 14),\r\n" + 
						"	(33, 22),\r\n" + 
						"	(33, 26),\r\n" + 
						"	(33, 33),\r\n" + 
						"	(34, 1),\r\n" + 
						"	(34, 2),\r\n" + 
						"	(34, 4),\r\n" + 
						"	(34, 7),\r\n" + 
						"	(34, 9),\r\n" + 
						"	(34, 14),\r\n" + 
						"	(35, 1),\r\n" + 
						"	(35, 2),\r\n" + 
						"	(35, 11),\r\n" + 
						"	(35, 14),\r\n" + 
						"	(36, 1),\r\n" + 
						"	(36, 2),\r\n" + 
						"	(36, 9),\r\n" + 
						"	(36, 14),\r\n" + 
						"	(36, 17),\r\n" + 
						"	(36, 26),\r\n" + 
						"	(37, 1),\r\n" + 
						"	(37, 2),\r\n" + 
						"	(37, 9),\r\n" + 
						"	(37, 14),\r\n" + 
						"	(37, 24),\r\n" + 
						"	(37, 26),\r\n" + 
						"	(38, 1),\r\n" + 
						"	(38, 2),\r\n" + 
						"	(38, 4),\r\n" + 
						"	(38, 7),\r\n" + 
						"	(38, 10),\r\n" + 
						"	(38, 14),\r\n" + 
						"	(38, 22),\r\n" + 
						"	(38, 26),\r\n" + 
						"	(39, 1),\r\n" + 
						"	(39, 2),\r\n" + 
						"	(39, 9),\r\n" + 
						"	(39, 14),\r\n" + 
						"	(39, 24),\r\n" + 
						"	(39, 26),\r\n" + 
						"	(40, 1),\r\n" + 
						"	(40, 2),\r\n" + 
						"	(40, 9),\r\n" + 
						"	(40, 11),\r\n" + 
						"	(40, 14),\r\n" + 
						"	(40, 26),\r\n" + 
						"	(41, 1),\r\n" + 
						"	(41, 2),\r\n" + 
						"	(41, 3),\r\n" + 
						"	(41, 9),\r\n" + 
						"	(41, 14),\r\n" + 
						"	(41, 17),\r\n" + 
						"	(41, 25),\r\n" + 
						"	(42, 1),\r\n" + 
						"	(42, 2),\r\n" + 
						"	(42, 9),\r\n" + 
						"	(42, 11),\r\n" + 
						"	(42, 14),\r\n" + 
						"	(42, 17),\r\n" + 
						"	(42, 20),\r\n" + 
						"	(42, 25),\r\n" + 
						"	(43, 1),\r\n" + 
						"	(43, 2),\r\n" + 
						"	(43, 9),\r\n" + 
						"	(43, 14),\r\n" + 
						"	(43, 26),\r\n" + 
						"	(44, 1),\r\n" + 
						"	(44, 2),\r\n" + 
						"	(44, 9),\r\n" + 
						"	(44, 14),\r\n" + 
						"	(44, 26),\r\n" + 
						"	(45, 1),\r\n" + 
						"	(45, 2),\r\n" + 
						"	(45, 11),\r\n" + 
						"	(45, 14),\r\n" + 
						"	(45, 26),\r\n" + 
						"	(45, 40),\r\n" + 
						"	(46, 1),\r\n" + 
						"	(46, 2),\r\n" + 
						"	(46, 4),\r\n" + 
						"	(46, 9),\r\n" + 
						"	(46, 14),\r\n" + 
						"	(46, 17),\r\n" + 
						"	(46, 26),\r\n" + 
						"	(47, 1),\r\n" + 
						"	(47, 2),\r\n" + 
						"	(47, 6),\r\n" + 
						"	(47, 8),\r\n" + 
						"	(47, 9),\r\n" + 
						"	(47, 14),\r\n" + 
						"	(47, 26),\r\n" + 
						"	(48, 1),\r\n" + 
						"	(48, 2),\r\n" + 
						"	(48, 6),\r\n" + 
						"	(48, 9),\r\n" + 
						"	(48, 11),\r\n" + 
						"	(48, 14),\r\n" + 
						"	(48, 26),\r\n" + 
						"	(49, 1),\r\n" + 
						"	(49, 2),\r\n" + 
						"	(49, 6),\r\n" + 
						"	(49, 9),\r\n" + 
						"	(49, 13),\r\n" + 
						"	(49, 14),\r\n" + 
						"	(49, 26),\r\n" + 
						"	(50, 1),\r\n" + 
						"	(50, 2),\r\n" + 
						"	(50, 9),\r\n" + 
						"	(50, 14),\r\n" + 
						"	(50, 22),\r\n" + 
						"	(50, 25),\r\n" + 
						"	(50, 26),\r\n" + 
						"	(50, 28),\r\n" + 
						"	(51, 1),\r\n" + 
						"	(51, 2),\r\n" + 
						"	(51, 4),\r\n" + 
						"	(51, 6),\r\n" + 
						"	(51, 7),\r\n" + 
						"	(51, 8),\r\n" + 
						"	(51, 9),\r\n" + 
						"	(51, 14),\r\n" + 
						"	(51, 17),\r\n" + 
						"	(51, 24),\r\n" + 
						"	(51, 26),\r\n" + 
						"	(52, 1),\r\n" + 
						"	(52, 2),\r\n" + 
						"	(52, 6),\r\n" + 
						"	(52, 9),\r\n" + 
						"	(52, 13),\r\n" + 
						"	(52, 14),\r\n" + 
						"	(52, 26),\r\n" + 
						"	(53, 1),\r\n" + 
						"	(53, 2),\r\n" + 
						"	(53, 4),\r\n" + 
						"	(53, 6),\r\n" + 
						"	(53, 7),\r\n" + 
						"	(53, 8),\r\n" + 
						"	(53, 9),\r\n" + 
						"	(53, 14),\r\n" + 
						"	(53, 21),\r\n" + 
						"	(53, 22),\r\n" + 
						"	(53, 24),\r\n" + 
						"	(53, 26),\r\n" + 
						"	(54, 1),\r\n" + 
						"	(54, 2),\r\n" + 
						"	(54, 4),\r\n" + 
						"	(54, 6),\r\n" + 
						"	(54, 7),\r\n" + 
						"	(54, 8),\r\n" + 
						"	(54, 9),\r\n" + 
						"	(54, 14),\r\n" + 
						"	(54, 26),\r\n" + 
						"	(55, 1),\r\n" + 
						"	(55, 2),\r\n" + 
						"	(55, 11),\r\n" + 
						"	(55, 14),\r\n" + 
						"	(55, 26),\r\n" + 
						"	(55, 41),\r\n" + 
						"	(56, 1),\r\n" + 
						"	(56, 2),\r\n" + 
						"	(56, 4),\r\n" + 
						"	(56, 6),\r\n" + 
						"	(56, 8),\r\n" + 
						"	(56, 9),\r\n" + 
						"	(56, 14),\r\n" + 
						"	(56, 41),\r\n" + 
						"	(56, 42),\r\n" + 
						"	(57, 1),\r\n" + 
						"	(57, 2),\r\n" + 
						"	(57, 9),\r\n" + 
						"	(57, 14),\r\n" + 
						"	(57, 26),\r\n" + 
						"	(58, 1),\r\n" + 
						"	(58, 2),\r\n" + 
						"	(58, 6),\r\n" + 
						"	(58, 8),\r\n" + 
						"	(58, 9),\r\n" + 
						"	(58, 14),\r\n" + 
						"	(58, 26),\r\n" + 
						"	(59, 1),\r\n" + 
						"	(59, 2),\r\n" + 
						"	(59, 9),\r\n" + 
						"	(59, 14),\r\n" + 
						"	(59, 26)");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE z_userconnector");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_userconnector (\r\n" + 
						"  userid int  NOT NULL,\r\n" + 
						"  label int  DEFAULT NULL,\r\n" + 
						"  artist int  DEFAULT NULL,\r\n" + 
						"  PRIMARY KEY (userid)\r\n" + 
						")");
		db.getConnection().createStatement().execute("DELETE FROM z_userconnector");
		
		
		try {
			db.getConnection().createStatement().execute("DROP TABLE  z_user_voted_songfeeling");
		} catch (SQLException e) {
			if (!e.getSQLState().equals("proper SQL-state for table does not exist")) {
			} else {
			}
		}
		db.getConnection().createStatement()
				.execute("CREATE TABLE z_user_voted_songfeeling (\r\n" + "  userid int  NOT NULL DEFAULT 0,\r\n"
						+ "  song bigint  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (userid,song)\r\n" + ")");
		db.getConnection().createStatement().execute("DELETE FROM z_user_voted_songfeeling");

	}

}
