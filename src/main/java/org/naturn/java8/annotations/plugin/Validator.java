package org.naturn.java8.annotations.plugin;

import java.util.function.Supplier;

import javax.xml.bind.ValidationException;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月17日 - 下午7:10:34
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public enum Validator {

	INTEGER_NUMBER {

		@Override
		void validate(Supplier<?> string) throws ValidationException {
			// TODO Auto-generated method stub
			try {
				Integer.parseInt((String) string.get());
			} catch (NumberFormatException e) {
				throw new ValidationException("Error while validationException. " + string.get());
			}
		}
	},
	POSITIVE_NUMBER {

		@Override
		void validate(Supplier<?> string) throws ValidationException {
			// TODO Auto-generated method stub
			try {
				if(Double.compare(0.0, Double.parseDouble((String)string.get()))>0){
					throw new Exception();
				}
			} catch (Exception ex) {
				throw new ValidationException("Error while validationException " + string.get());
			}
		}
	};
	abstract void validate(Supplier<?> string) throws ValidationException;
}
