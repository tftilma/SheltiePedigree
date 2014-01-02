/**
 * Name: pedigree.js
 * Author: Tsjisse Tilma
 * Date: 02 09-2003
 * Purpose: General pedigree functions
 */

function pow(n)
{
   var i;
   var power=1;
   for (i=1; i<=n; i++)
   {
     power = power * 2;
   }
   return power;
}

function ShowStamboomRecurr(nGenerations, generation, arrName, arrId, idx)
{
    var rows = pow(nGenerations - generation);
    document.write('<TD VALIGN="MIDDLE" ROWSPAN="' + rows + '"> ');
    if (arrId[idx] == null)
    {
	  document.write('  <FONT size="3" face="Bookman Old Style" COLOR="#006a76">');
	  document.write('      ????');
	  document.write('  </FONT>');
    }
    else
    {
	  document.write('  <A HREF="' +arrId[idx] +'.html">');
	  document.write('  <FONT size="3" face="Bookman Old Style" COLOR="#006a76">');
	  document.write('    &nbsp; &nbsp; ');
	  document.write('      ' + arrName[idx] + '');
	  document.write('  </FONT>');
	  document.write('  </A>');
    }
    document.write('</TD>');

    if (generation < nGenerations)
    {
      ShowStamboomRecurr(nGenerations, generation + 1, arrName, arrId, 2*idx );
      ShowStamboomRecurr(nGenerations, generation + 1, arrName, arrId, 2*idx + 1);
    }
    else
    {
      document.write('</TR>');
      if (idx < pow(generation+1) - 1)
      {
        // Always write when last generation, except when ultimate last
        // (like e.g. MMMM)
        document.write('<TR>');
      }
   }
}

function ShowStamboom(nGenerations, arrName, arrId)
{
  ShowStamboomRecurr(nGenerations, 1, arrName, arrId, 2);
  ShowStamboomRecurr(nGenerations, 1, arrName, arrId, 3);
}

function ShowStamboomRecurrInfo(nGenerations, generation, arrName, arrInfo, idx)
{
    var rows = pow(nGenerations - generation);
    document.write('<TD VALIGN="MIDDLE" ROWSPAN="' + rows + '"> ');
    document.write('  <FONT face="Bookman Old Style" COLOR="#006a76">');
    document.write('    &nbsp; &nbsp; ');
    document.write('    <FONT SIZE="3"> ');
    document.write('      ' + arrName[idx] + '');
    document.write('    </FONT> <BR>');
    document.write('    &nbsp; &nbsp; ');
    document.write('    <FONT SIZE="2"> ');
    document.write('      ' + arrInfo[idx] + '');
    document.write('    </FONT>');
    document.write('  </FONT>');
    document.write('</TD>');

    if (generation < nGenerations)
    {
      ShowStamboomRecurrInfo(nGenerations, generation + 1, arrName, arrInfo, 2*idx );
      ShowStamboomRecurrInfo(nGenerations, generation + 1, arrName, arrInfo, 2*idx + 1);
    }
    else
    {
      document.write('</TR>');
      if (idx < pow(generation+1) - 1)
      {
        // Always write when last generation, except when ultimate last
        // (like e.g. MMMM)
        document.write('<TR>');
      }
   }
}

function ShowStamboomInfo(nGenerations, arrName, arrInfo)
{
  ShowStamboomRecurrInfo(nGenerations, 1, arrName, arrInfo, 2)
  ShowStamboomRecurrInfo(nGenerations, 1, arrName, arrInfo, 3)
}

