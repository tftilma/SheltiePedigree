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

function ShowStamboomRecurr(nGenerations, generation, arrName, arrId, arrColor, idx)
{
  var rows = pow(nGenerations - generation);
  document.write('<td width="20%" valign="middle" rowspan="' + rows + '" bgcolor="' + arrColor[idx] + '">');
  if (arrId[idx] == null)
  {
    document.write('<font size="3" face="Bookman Old Style" color="#006a76">');
    document.write('      ????');
    document.write('</font>');
  }
  else
  {
    document.write('<a href="' +arrId[idx] +'.html">');
    document.write('<font size="3" face="Bookman Old Style" color="#006a76">');
    document.write('<center>');
    document.write('      ' + arrName[idx] + '');
    document.write('</center></font></a>');
  }
  document.write('</td>');

  if (generation < nGenerations)
  {
    ShowStamboomRecurr(nGenerations, generation + 1, arrName, arrId, arrColor, 2*idx );
    ShowStamboomRecurr(nGenerations, generation + 1, arrName, arrId, arrColor, 2*idx + 1);
  }
  else
  {
    document.write('</tr>');
    if (idx < pow(generation+1) - 1)
    {
      // Always write when last generation, except when ultimate last
      // (like e.g. MMMM)
      document.write('<tr>');
    }
  }
}

function ShowStamboom(nGenerations, arrName, arrId)
{
  var arrColor = new Array(64);
  var colors = new Array(32);
  var white = '#FFFFFF';
  var idx;
  for (idx=1; idx<64; idx++)
  {
    arrColor[idx] = white;
    colors[idx] = '#999999';
  }
  colors[1]  = '#EF7575';
  colors[2]  = '#EFEF75';
  colors[3]  = '#75EF75';
  colors[4]  = '#75EFEF';
  colors[5]  = '#7575EF';
  colors[6]  = '#EF75EF';
  //colors[7]  = '#D50000';
  colors[7]  = '#EFA475';

  //colors[8]  = '#D510E0';
  //colors[8]  = '#DCEF75'; // geel
  colors[8]  = '#FFFF75'; // geel

  //colors[9]  = '#009075';
  colors[9]  = '#EF7596'; // rose
  //colors[9] = '#FFFFFF';

  //colors[10] = '#E07500';
  colors[10] = '#CF75EF';
  colors[11] = '#E00075';
  colors[12] = '#0075E0';
  colors[13] = '#757500';
  colors[14] = '#CF75EF';
  colors[15] = '#959595';
  colors[16] = '#CFE975';

  var sameIdx = 0;
  var cmpIdx;
  for (idx=2; idx<62; idx++)
  {
    var isMale = idx % 2;
    var willSet = false;
    // first parse: just set only those that need to be set
    for (cmpIdx=idx+2; cmpIdx<64; cmpIdx+=2)
    {
      if (!willSet &&
          arrId[idx] != null &&
          arrId[cmpIdx] != null &&
          arrColor[cmpIdx] == white &&
          arrColor[idx] == white &&
          arrId[cmpIdx] == arrId[idx])
      {
        if (isMale == 0)
	    {
          if (idx == 2)
	      {
	        willSet = true;
          }
          else
          {
  	        willSet = (arrId[idx / 2] != arrId[cmpIdx / 2]);
          }
        }
	    else
        {
          if (idx == 3)
          {
            willSet = true;
          }
          else
          {
            willSet = (arrId[(idx-1) / 2] != arrId[(cmpIdx-1) / 2])
          }
        }
      }
    }
    if (willSet)
    {
      // Ok found one now set color
      sameIdx++;
      // Set color for (other) animals which are the sane as the tested one
      for (cmpIdx=idx+2; cmpIdx<64; cmpIdx+=2)
      {
        if (arrId[idx] != null &&
            arrId[cmpIdx] != null &&
            arrColor[cmpIdx] == white &&
            arrColor[idx] == white &&
            arrId[cmpIdx] == arrId[idx])
        {
          arrColor[cmpIdx] = colors[sameIdx];
        }
      }
      // First and last possibility to set the color for the tested one
      arrColor[idx] = colors[sameIdx];
      willSet = false;
    }
  }

  ShowStamboomRecurr(nGenerations, 1, arrName, arrId, arrColor, 2);
  ShowStamboomRecurr(nGenerations, 1, arrName, arrId, arrColor, 3);
}

function ShowStamboomRecurrInfo(nGenerations, generation, arrName, arrInfo, idx)
{
    var rows = pow(nGenerations - generation);
    document.write('<td valign="middle" rowspan="' + rows + '"> ');
    document.write('<font face="Bookman Old Style" color="#006a76">&nbsp; &nbsp; ');
    document.write('<font size="3">' + arrName[idx] + '</font><br/>');
    document.write('&nbsp; &nbsp;');
    document.write('<font size="2"> ' + arrInfo[idx] + '</font>');
    document.write('</font>');
    document.write('</td>');

    if (generation < nGenerations)
    {
      ShowStamboomRecurrInfo(nGenerations, generation + 1, arrName, arrInfo, 2*idx );
      ShowStamboomRecurrInfo(nGenerations, generation + 1, arrName, arrInfo, 2*idx + 1);
    }
    else
    {
      document.write('</tr>');
      if (idx < pow(generation+1) - 1)
      {
        // Always write when last generation, except when ultimate last
        // (like e.g. MMMM)
        document.write('<tr>');
      }
   }
}

function ShowStamboomInfo(nGenerations, arrName, arrInfo)
{
  ShowStamboomRecurrInfo(nGenerations, 1, arrName, arrInfo, 2)
  ShowStamboomRecurrInfo(nGenerations, 1, arrName, arrInfo, 3)
}

