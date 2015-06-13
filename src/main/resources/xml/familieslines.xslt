<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="iso-8859-1"/>

<xsl:template match="totals">
    <TABLE>
      <TR>
        <TD>
          Number of Shelties entered
        </TD>
        <TD>
          <xsl:value-of select="nAnimals"/>
        </TD>
      </TR>
      <TR>
        <TD>
          Number of Kennels entered
        </TD>
        <TD>
          <xsl:value-of select="nKennels"/>
        </TD>
      </TR>
    </TABLE>
</xsl:template>

<!--
<xsl:template match="statistics">
    <xsl:apply-templates select="totals"/>
</xsl:template>
-->

<xsl:template match="statistics">    
<HTML>
  <HEAD>
      <META NAME="description"
            CONTENT="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
 
      <META NAME="keywords"
            CONTENT="Portma Zathe, Sheltie, Shetland Sheepdog"/>
     
      <!-- No underline -->
      <STYLE type="text/css">
          A { text-decoration:none }
      </STYLE>

      <script language="JavaScript" SRC="../js/navigate.js">
      </script>
      
      <TITLE>Statistics</TITLE>
  </HEAD>

  <BODY BGCOLOR="#10FEEE">
    <!-- <xsl:apply-templates select="statistics"/> -->
    <xsl:apply-templates select="totals"/> 
  </BODY>
</HTML>
</xsl:template>


</xsl:stylesheet>

