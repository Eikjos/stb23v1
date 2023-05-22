<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Spécification technique des besoins</title>
				<meta charset="utf-8"/>
			</head>
			<body>
				<xsl:call-template name="title"/>
				<xsl:call-template name="team"/>
				<xsl:call-template name="foncionnalité"/>
			</body>
		</html>
	</xsl:template>
	<xsl:template name="title">
		<h1>
			<xsl:value-of select="stb/title"/>
		</h1>
		<p>
			Version : <xsl:value-of select="stb/version"/>
		</p>
		<p>
			Date de validation :
			<xsl:variable name="datetime" select="stb/date" />
			<xsl:variable name="annee" select="substring($datetime, 0, 5)"/>
			<xsl:variable name="mois" select="substring($datetime, 6, 2)"/>
			<xsl:variable name="jour" select="substring($datetime, 9, 2)"/>
			<xsl:value-of select="$jour"/>
			<xsl:choose>
				<xsl:when test="$mois = '01'">
					<xsl:text> Jan </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '02'">
					<xsl:text> Fevr </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '03'">
					<xsl:text> Mars </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '04'">
					<xsl:text> Avr </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '05'">
					<xsl:text> Mai </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '06'">
					<xsl:text> Juin </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '07'">
					<xsl:text> Jul </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '08'">
					<xsl:text> Aout </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '09'">
					<xsl:text> Sep </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '10'">
					<xsl:text> Oct </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '11'">
					<xsl:text> Nov </xsl:text>
				</xsl:when>
				<xsl:when test="$mois = '12'">
					<xsl:text> Dec </xsl:text>
				</xsl:when>
			</xsl:choose>
			<xsl:value-of select="$annee"/>
		</p>
		<p>
			<xsl:value-of select="stb/description"/>
		</p>
		<h2>Client :
			<xsl:value-of select="stb/client/person/@gender"/>
			<xsl:text> </xsl:text>
			<xsl:value-of select="stb/client/person/@lastname"/>
			<xsl:text> </xsl:text>
			<xsl:value-of select="stb/client/person"/>
		</h2>
		<p>
			<xsl:value-of select="stb/client/entity"/>
			<xsl:variable name="mail" select="stb/client/mail"/>
			<xsl:if test="$mail">
				<xsl:text> - </xsl:text>
				<xsl:value-of select="$mail"/>
			</xsl:if>
			<xsl:variable name="tel" select="stb/client/tel"/>
			<xsl:if test="$tel">
				<xsl:text> - </xsl:text>
				<xsl:if test="contains($tel, '+33')">
					<xsl:text>+33 (0)</xsl:text>
					<xsl:value-of select="substring($tel, 3)"/>
				</xsl:if>
				<xsl:if test="not(contains($tel, '+33'))">
					<xsl:value-of select="$tel"/>
				</xsl:if>
			</xsl:if>
		</p>
	</xsl:template>
	<xsl:template name="team">
		<h2>Membres du projet :</h2>
		<ul>
			<xsl:for-each select="stb/team/member">
				<li>
					<xsl:value-of select="person/@gender"/>
					<xsl:text> </xsl:text>
					<xsl:value-of select="person"/>
					<xsl:text> </xsl:text>
					<strong>
						<xsl:value-of select="person/@lastname"/>
					</strong>
					<xsl:text> - </xsl:text>
					<xsl:for-each select="function">
						<xsl:value-of select="text()"/>
						<xsl:text> - </xsl:text>
					</xsl:for-each>
					<xsl:text> </xsl:text>
					(<xsl:value-of select="mail"/>)
				</li>
			</xsl:for-each>
		</ul>
	</xsl:template>
	<xsl:template name="foncionnalité">
		<h2>Liste des fonctionnalités</h2>
		<ol>
			<xsl:for-each select="stb/features/feature">
				<li><xsl:value-of select="@number"/>.<xsl:value-of select="@section"/> :
					<xsl:value-of select="@name"/>
				</li>
			</xsl:for-each>
		</ol>
		<xsl:for-each select="stb/features/feature">
			<table style="border: solid 1px black;margin-top:10px">
				<thead style="background-color:lightgrey">
					<tr>
						<td colspan="2">Item=<xsl:value-of select="@number"/>.<xsl:value-of select="@section"/><xsl:text> </xsl:text><xsl:value-of
								select="@name"/>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Priorité :
							<xsl:value-of select="priority"/>
						</td>
						<xsl:variable name="datetime" select="delivery"/>
						<xsl:variable name="annee" select="substring($datetime, 0, 5)"/>
						<xsl:variable name="mois" select="substring($datetime, 6, 2)"/>
						<xsl:variable name="jour" select="substring($datetime, 9, 2)"/>
						<td>
							Livraison :
							<xsl:value-of select="$jour"/>
							<xsl:choose>
								<xsl:when test="$mois = '01'">
									<xsl:text> Jan </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '02'">
									<xsl:text> Fevr </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '03'">
									<xsl:text> Mars </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '04'">
									<xsl:text> Avr </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '05'">
									<xsl:text> Mai </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '06'">
									<xsl:text> Juin </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '07'">
									<xsl:text> Jul </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '08'">
									<xsl:text> Aout </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '09'">
									<xsl:text> Sep </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '10'">
									<xsl:text> Oct </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '11'">
									<xsl:text> Nov </xsl:text>
								</xsl:when>
								<xsl:when test="$mois = '12'">
									<xsl:text> Dec </xsl:text>
								</xsl:when>
							</xsl:choose>
							<xsl:value-of select="$annee"/>
						</td>
					</tr>
					<tr>
						<td>
							<xsl:value-of select="description"/>
							<br/>
							<xsl:value-of select="comment"/>
						</td>
					</tr>
					<tr/>
				</tbody>
			</table>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>