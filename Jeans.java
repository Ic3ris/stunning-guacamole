// The "Jeans" class.
//Noah Cardoso
//Friday, September 2, 2022
import java.awt.*;
import hsa.Console;

public class Jeans
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();




	int numLife = 20;

	//days
	for (int mainMan = 0 ; mainMan < 50 ; mainMan++)
	{
	    int[] [] start = new int [numLife + 1] [3];
	    for (int x = 0 ; x < numLife + 1 ; x++)
	    {
		for (int y = 0 ; y < 3 ; y++)
		{
		    start [x] [y] = 0;
		}
	    }
	    int[] [] plane = new int [15] [15];
	    for (int x = 0 ; x < 15 ; x++)
	    {
		for (int y = 0 ; y < 15 ; y++)
		{
		    plane [x] [y] = 0;
		}
	    }
	    for (int n = 0 ; n <= numLife ; n++)
	    {

		int[] startPosition = new int [2];
		do
		{

		    if (n < numLife / 2)
		    {
			startPosition = new int[]
			{
			    (int) (Math.random () * 2) * 14, (int) (Math.random () * 15 + 0)
			}
			;
		    }
		    else if (n >= numLife / 2)
		    {
			startPosition = new int[]
			{
			    (int) (Math.random () * 15 + 0), (int) (Math.random () * 2) * 14
			}
			;
		    }
		}
		while (plane [startPosition [0]] [startPosition [1]] != 0);
		start [n] [0] = startPosition [0];
		start [n] [1] = startPosition [1];
		plane [startPosition [0]] [startPosition [1]] = n;

	    }
	    //food
	    for (int numFood = 30 ; numFood != 0 ; numFood--)
	    {
		int a = (int) (Math.random () * 9) + 3, b = (int) (Math.random () * 9) + 3;
		if (plane [a] [b] == 0)
		    plane [a] [b] = -1;
		else
		    numFood++;

	    }
	    drawPlane (plane);
	    //hour
	    int[] [] last = new int [numLife] [2];
	    for (int energy = 14 * 2 ; energy >= 0 ; energy--)
	    {


		for (int n = 0 ; n < numLife ; n++)
		{
		    int[] shift = new int [2];
		    int[] position = find (n, plane);
		    for (int x = -1 ; x <= 1 ; x++)
		    {
			for (int y = -1 ; y <= 1 ; y++)
			{
			    if ((position [0] + x > -1 && position [0] + x < 15) && (position [1] + y > -1 && position [1] + y < 15))
			    {
				if (plane [position [0] + x] [position [1] + y] == -1)
				{
				    shift [0] = x;
				    shift [1] = y;
				}
			    }
			}
		    }
		    if (shift [0] == 0 && shift [1] == 0 || start [n] [2] >= 2)
		    {
			if (energy == 14 * 2)
			{
			    shift = new int[]
			    {
				(int) (Math.random () * 3) - 1, (int) (Math.random () * 3) - 1
			    }
			    ;


			}
			else if (energy > 4 * 2)
			{
			    int x, y;
			    int chance = (int) (Math.random () * 3 + 0);
			    if (chance == 0 && (last [n] [0] != 0 || last [n] [0] != 0))
			    {
				x = last [n] [0];
				y = last [n] [1];
			    }
			    else
			    {
				x = (int) (Math.random () * 3) - 1;
				y = (int) (Math.random () * 3) - 1;
			    }

			    shift = new int[]
			    {
				x, y
			    }
			    ;

			}
			else if (energy <= 4 * 2 || start [n] [2] >= 2)
			{
			    int x = 0, y = 0;


			    if (start [n] [0] > position [0])
			    {
				x = 1;
			    }
			    else if (start [n] [0] < position [0])
			    {
				x = -1;
			    }
			    if (start [n] [1] > position [1])
			    {
				y = 1;
			    }
			    else if (start [n] [1] < position [0])
			    {
				y = -1;
			    }




			    shift = new int[]
			    {
				x, y
			    }
			    ;
			}
		    }
		    int[] newPosition = new int[]
		    {
			position [0] + shift [0], position [1] + shift [1]
		    }
		    ;

		    if ((newPosition [0] < 15 && newPosition [0] >= 0) && (newPosition [1] < 15 && newPosition [1] >= 0))
		    {

			if (plane [newPosition [0]] [newPosition [1]] <= 0)
			{
			    if (plane [newPosition [0]] [newPosition [1]] == -1)
				start [n] [2]++;
			    plane [newPosition [0]] [newPosition [1]] = n;
			    last [n] [0] = shift [0];
			    last [n] [1] = shift [1];


			    plane [position [0]] [position [1]] = 0;
			}

		    }




		}

		c.clear ();
		drawPlane (plane);
		delay (50);
	    }
	    int increase = 0;
	    for (int n = 0 ; n <= numLife ; n++)
	    {
		if (mainMan != 0)
		{
		    if (start [n] [2] == 0)
			increase--;
		    else if (start [n] [2] >= 2)
			increase++;
		}
	    }
	    numLife += increase;
	}
    } // main method


    public static int[] find (int n, int[] [] plane)
    {
	int[] pos = new int[]
	{
	    0, 0
	}


	;
	for (int x = 0 ; x < 15 ; x++)
	{
	    for (int y = 0 ; y < 15 ; y++)
	    {

		if (plane [x] [y] == n)
		{
		    pos = new int[]
		    {
			x, y
		    }
		    ;
		    return (pos);
		}
	    }
	}


	return (pos);
    }


    public static void drawPlane (int[] [] plane)
    {
	int s = 25;
	for (int x = 0 ; x < 15 ; x++)
	{
	    for (int y = 0 ; y < 15 ; y++)
	    {
		if (plane [x] [y] == 0)
		{
		    c.setColor (Color.white);
		    c.fillRect (x * s, y * s, s, s);

		}
		else if (plane [x] [y] == -1)
		{
		    c.setColor (Color.yellow);
		    c.fillRect (x * s, y * s, s, s);

		}
		else if (plane [x] [y] > 0)
		{
		    c.setColor (Color.green);
		    c.fillOval (x * s, y * s, s, s);
		}
		c.setColor (Color.black);
		c.drawRect (x * s, y * s, s, s);
	    }
	}
    }


    public static void delay (int millisecs)
    {
	try
	{
	    Thread.currentThread ().sleep (millisecs);
	}


	catch (InterruptedException e)
	{
	}
    }
} // Jeans class


