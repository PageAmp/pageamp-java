// Generated by Haxe 4.1.5
package haxe.root;

import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public class Lambda extends haxe.lang.HxObject
{
	public Lambda(haxe.lang.EmptyObject empty)
	{
	}
	
	
	public Lambda()
	{
		//line 39 "/usr/local/lib/haxe/std/Lambda.hx"
		haxe.root.Lambda.__hx_ctor__Lambda(this);
	}
	
	
	protected static void __hx_ctor__Lambda(haxe.root.Lambda __hx_this)
	{
	}
	
	
	public static <A> haxe.root.Array<A> array(java.lang.Object it)
	{
		//line 46 "/usr/local/lib/haxe/std/Lambda.hx"
		haxe.root.Array<A> a = new haxe.root.Array<A>();
		//line 47 "/usr/local/lib/haxe/std/Lambda.hx"
		{
			//line 47 "/usr/local/lib/haxe/std/Lambda.hx"
			java.lang.Object i = ((java.lang.Object) (haxe.lang.Runtime.callField(it, "iterator", null)) );
			//line 47 "/usr/local/lib/haxe/std/Lambda.hx"
			while (haxe.lang.Runtime.toBool(((java.lang.Boolean) (haxe.lang.Runtime.callField(i, "hasNext", null)) )))
			{
				//line 47 "/usr/local/lib/haxe/std/Lambda.hx"
				A i1 = ((A) (haxe.lang.Runtime.callField(i, "next", null)) );
				//line 48 "/usr/local/lib/haxe/std/Lambda.hx"
				a.push(i1);
			}
			
		}
		
		//line 49 "/usr/local/lib/haxe/std/Lambda.hx"
		return a;
	}
	
	
	public static <A> boolean has(java.lang.Object it, A elt)
	{
		//line 109 "/usr/local/lib/haxe/std/Lambda.hx"
		{
			//line 109 "/usr/local/lib/haxe/std/Lambda.hx"
			java.lang.Object x = ((java.lang.Object) (haxe.lang.Runtime.callField(it, "iterator", null)) );
			//line 109 "/usr/local/lib/haxe/std/Lambda.hx"
			while (haxe.lang.Runtime.toBool(((java.lang.Boolean) (haxe.lang.Runtime.callField(x, "hasNext", null)) )))
			{
				//line 109 "/usr/local/lib/haxe/std/Lambda.hx"
				A x1 = ((A) (haxe.lang.Runtime.callField(x, "next", null)) );
				//line 110 "/usr/local/lib/haxe/std/Lambda.hx"
				if (haxe.lang.Runtime.eq(x1, elt)) 
				{
					//line 111 "/usr/local/lib/haxe/std/Lambda.hx"
					return true;
				}
				
			}
			
		}
		
		//line 112 "/usr/local/lib/haxe/std/Lambda.hx"
		return false;
	}
	
	
	public static <A> boolean exists(java.lang.Object it, haxe.lang.Function f)
	{
		//line 126 "/usr/local/lib/haxe/std/Lambda.hx"
		{
			//line 126 "/usr/local/lib/haxe/std/Lambda.hx"
			java.lang.Object x = ((java.lang.Object) (haxe.lang.Runtime.callField(it, "iterator", null)) );
			//line 126 "/usr/local/lib/haxe/std/Lambda.hx"
			while (haxe.lang.Runtime.toBool(((java.lang.Boolean) (haxe.lang.Runtime.callField(x, "hasNext", null)) )))
			{
				//line 126 "/usr/local/lib/haxe/std/Lambda.hx"
				A x1 = ((A) (haxe.lang.Runtime.callField(x, "next", null)) );
				//line 127 "/usr/local/lib/haxe/std/Lambda.hx"
				if (haxe.lang.Runtime.toBool(((java.lang.Boolean) (f.__hx_invoke1_o(0.0, x1)) ))) 
				{
					//line 128 "/usr/local/lib/haxe/std/Lambda.hx"
					return true;
				}
				
			}
			
		}
		
		//line 129 "/usr/local/lib/haxe/std/Lambda.hx"
		return false;
	}
	
	
}

