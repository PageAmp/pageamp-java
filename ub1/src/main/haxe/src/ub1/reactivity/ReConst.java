// Generated by Haxe 4.1.5
package ub1.reactivity;

import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public class ReConst extends ub1.reactivity.ReValue
{
	public ReConst(haxe.lang.EmptyObject empty)
	{
		//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		super(haxe.lang.EmptyObject.EMPTY);
	}
	
	
	public ReConst(ub1.reactivity.ReScope scope, java.lang.String k, java.lang.Object v, java.lang.String handler, java.lang.Object refreshable, java.lang.Object handleFirst, java.lang.Object parse)
	{
		//line 7 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		super(haxe.lang.EmptyObject.EMPTY);
		//line 7 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		ub1.reactivity.ReConst.__hx_ctor_ub1_reactivity_ReConst(this, scope, k, v, handler, refreshable, handleFirst, parse);
	}
	
	
	protected static void __hx_ctor_ub1_reactivity_ReConst(ub1.reactivity.ReConst __hx_this, ub1.reactivity.ReScope scope, java.lang.String k, java.lang.Object v, java.lang.String handler, java.lang.Object refreshable, java.lang.Object handleFirst, java.lang.Object parse)
	{
		//line 6 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		boolean parse1 = ( (haxe.lang.Runtime.eq(parse, null)) ? (true) : (haxe.lang.Runtime.toBool(((java.lang.Boolean) (parse) ))) );
		//line 6 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		boolean handleFirst1 = ( (haxe.lang.Runtime.eq(handleFirst, null)) ? (true) : (haxe.lang.Runtime.toBool(((java.lang.Boolean) (handleFirst) ))) );
		//line 6 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		boolean refreshable1 = ( (haxe.lang.Runtime.eq(refreshable, null)) ? (false) : (haxe.lang.Runtime.toBool(((java.lang.Boolean) (refreshable) ))) );
		//line 7 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		ub1.reactivity.ReValue.__hx_ctor_ub1_reactivity_ReValue(__hx_this, scope, k, v, handler, refreshable1, handleFirst1, parse1);
		//line 8 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		__hx_this.unlink();
	}
	
	
	@Override public java.lang.Object set(java.lang.Object v, java.lang.Object push)
	{
		//line 12 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		boolean push1 = ( (haxe.lang.Runtime.eq(push, null)) ? (false) : (haxe.lang.Runtime.toBool(((java.lang.Boolean) (push) ))) );
		//line 12 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		return v;
	}
	
	
	@Override public java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
		{
			//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
			boolean __temp_executeDef1 = true;
			//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
			if (( field != null )) 
			{
				//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
				switch (field.hashCode())
				{
					case 113762:
					{
						//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
						if (field.equals("set")) 
						{
							//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
							__temp_executeDef1 = false;
							//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
							return ((haxe.lang.Function) (new haxe.lang.Closure(this, "set")) );
						}
						
						//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
						break;
					}
					
					
				}
				
			}
			
			//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
			if (__temp_executeDef1) 
			{
				//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			else
			{
				//line 3 "/Users/fabrizio/ubimate/oss/ub1/ub1-core/src/ub1/reactivity/ReConst.hx"
				throw null;
			}
			
		}
		
	}
	
	
}

