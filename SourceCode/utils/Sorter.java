package utils;

import java.util.*;
import java.lang.reflect.*;
import java.util.Date;

public class Sorter
{
	private String strMethodName = null;
	private final static String CLASS_NAME  = "Sorter.";

//======================================================================================

	public Sorter()
	{
	}

//======================================================================================
	Comparator OBJECT_COMPARE = new Comparator()
	{

		public int compare(Object o1, Object o2)
		{
			int i1 = 0;
			int i2 = 0;
			int intReturnValue = 0;

			//Based on return types

			//String
			if (getReturnTypeByReflection(o1).equalsIgnoreCase("string"))
			{
				String s1 = (String) getObjectByReflection(o1);
				String s2 = (String) getObjectByReflection(o2);
				intReturnValue = s1.compareTo(s2);
			}

            		//integer
			else if (getReturnTypeByReflection(o1).indexOf("Integer") != -1)
			{
				i1 = ((Integer)getObjectByReflection(o1)).intValue();
				i2 = ((Integer)getObjectByReflection(o2)).intValue();
				intReturnValue = i1 - i2;
			}

            		//int
            		else if (getReturnTypeByReflection(o1).indexOf("int") != -1)
            		{
                		i1 = ((Integer)getObjectByReflection(o1)).intValue();
                		i2 = ((Integer)getObjectByReflection(o2)).intValue();
                		intReturnValue = i1 - i2;
			}


			//long
			else if (getReturnTypeByReflection(o1).equalsIgnoreCase("long"))
			{
				long l1 = ((Long)getObjectByReflection(o1)).longValue();
				long l2 = ((Long)getObjectByReflection(o2)).longValue();
				if ( (l1 - l2) > 0 )
					intReturnValue = 1;
				else if ((l1 - l2) < 0 )
					intReturnValue = -1;
			}

			//double
			else if (getReturnTypeByReflection(o1).equalsIgnoreCase("double"))
			{
				double d1 = ((Double)getObjectByReflection(o1)).doubleValue();
				double d2 = ((Double)getObjectByReflection(o2)).doubleValue();
				if ( (d1 - d2) > 0 )
					intReturnValue = 1;
				else if ((d1 - d2) < 0 )
					intReturnValue = -1;
			}

			//float
			else if (getReturnTypeByReflection(o1).equalsIgnoreCase("float"))
			{
				float f1 = ((Float)getObjectByReflection(o1)).floatValue();
				float f2 = ((Float)getObjectByReflection(o2)).floatValue();
				if ( (f1 - f2) > 0 )
					intReturnValue = 1;
				else if ((f1 - f2) < 0 )
					intReturnValue = -1;
			}

			//date
			else if (getReturnTypeByReflection(o1).equalsIgnoreCase("date"))
			{
				Date date1 = new Date();
				Date date2 = new Date();
				date1 = (Date)getObjectByReflection(o1);
				date2 = (Date)getObjectByReflection(o2);
				intReturnValue = date1.compareTo(date2);
			}

			return intReturnValue;
		}

	};

//======================================================================================

	protected Object getObjectByReflection(Object obj)
	{
		String METHOD = CLASS_NAME + "getObjectByReflection(Object obj) ";
		Object returnObject = null;

		try
		{
			Class c = obj.getClass();
			Method m= c.getMethod(strMethodName,new Class[] {});
			returnObject= m.invoke( obj,new Object[] {});
		}
		catch (Exception ex)
		{
			System.out.println(METHOD + " has a problem: " + ex.getMessage());
			ex.printStackTrace();
		}
		return  returnObject;
	}
//======================================================================================

	protected String getReturnTypeByReflection(Object obj)
	{
		String METHOD = CLASS_NAME + "getReturnTypeByReflection(Object obj) ";
		String strReturnType = null;

                if(obj == null){
                 System.out.println("the object is null in sorter METHOD");
                }


		try
		{
			Class c = obj.getClass();

                        //Logger.DSystem.println( strMethodName ) ;

			Method m= c.getMethod(strMethodName,new Class[] {});
			strReturnType = m.getReturnType().getName();

                        //Logger.DSystem.println(  strReturnType ) ;

			if(strReturnType.indexOf("String") != -1)
				strReturnType = "string";
			else if(strReturnType.indexOf("Date") != -1)
				strReturnType = "date";
			else if(strReturnType.indexOf("long") != -1)
				strReturnType = "long";
			else if(strReturnType.indexOf("int") != -1)
				strReturnType = "int";
			else if(strReturnType.indexOf("Integer") != -1)
				strReturnType = "Integer";
			else if(strReturnType.indexOf("float") != -1)
				strReturnType = "float";
			else if(strReturnType.indexOf("double") != -1)
				strReturnType = "double";
		}
		catch (Exception ex)
		{
			System.out.println(METHOD + " has a problem: " + ex.getMessage());
			ex.printStackTrace();
		}


		return  strReturnType;
	}

//======================================================================================
	private void setMethodName(String strNewName)
	{
		strMethodName= strNewName;
	}

//======================================================================================

	/**
	* This method will sort the ArrayList of any object based on key section.
	* arry:	the ArrayList to be sorted.
	* strKey: the field based on which we do the sorting.
	*         (this field should be exactly the same as the attribute name in dataobject)
	* strTurnFlag: the flag for the reverse order.
	* @return sorted ArrayList of any Objects.
	*/
	public ArrayList sortObject( ArrayList arry, String strKey, String strTurnFlag)
	{

		String METHOD = CLASS_NAME + "sortObject(ArrayList arry, String strKey, String strTurnFlag) ";

		ArrayList arrylist = new ArrayList();
		arrylist = arry;

		try
		{
			if (strTurnFlag.equalsIgnoreCase("REVERSE"))
			{
				//This is the key to find the correct section to sort
				setMethodName("get" + strKey);
				Collections.sort(arrylist,OBJECT_COMPARE);
				Collections.reverse(arrylist);
			}
			else
			{
				//This is the key to find the correct section to sort
				setMethodName("get" + strKey);
				Collections.sort(arrylist,OBJECT_COMPARE);
			}
		}
		catch (Exception ex)
		{
			System.out.println(METHOD + " has a problem:  " + ex.getMessage());
			ex.printStackTrace();
		}
		return arrylist;
	}

//==========================================================================================

	public ArrayList sortObject( ArrayList arry, String strKey)
	{
		String METHOD = CLASS_NAME + "sortObject(ArrayList arry, String strKey)";
		ArrayList arrylist = new ArrayList();
		arrylist = arry;

                 //if( arrylist != null && arrylist.size() > 20 ) Logger.DSystem.println("METHOD " + arrylist.size() );
		try
		{
			//This is the key to find the correct section to sort
			setMethodName("get" + strKey);
			Collections.sort(arrylist,OBJECT_COMPARE);
		}
		catch (Exception ex)
		{
			System.out.println(METHOD + " has a problem: " + ex.getMessage());
			ex.printStackTrace();
		}
		return arrylist;
	}

//======================================================================================

}
