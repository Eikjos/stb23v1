<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Liste  des STB</title>
                <meta charset="utf-8"/>
                <link rel="stylesheet" href="../style.css" type="text/css" />
            </head>
            <body>
                <header>
                    <h1>Gestionnaire de STB (Spécification Technique des Besoins)</h1>
                    <img src="/logo.png" alt="Logo de l'université de Rouen" />
                </header>
                <main>
                    <div style="width:100%">
                        <h2>Liste des spécifications techniques</h2>
                        <xsl:call-template name="list"/>
                    </div>
                </main>
                <footer>
                    <p>Projet Langage WEB - 2023 - Université de Rouen</p>
                </footer>
            </body>
        </html>
    </xsl:template>
    <xsl:template name="list">
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Titre</td>
                    <td>Description</td>
                    <td>Date</td>
                    <td>Client</td>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="/result/stb">
                    <tr>
                        <td><xsl:value-of select="@id" /></td>
                        <td><xsl:value-of select="title/text()"/></td>
                        <td><xsl:value-of select="description/text()"/></td>
                        <td>
                            <xsl:variable name="datetime" select="date" />
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
                        </td>
                        <td><xsl:value-of select="client/text()"/></td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>
</xsl:stylesheet>