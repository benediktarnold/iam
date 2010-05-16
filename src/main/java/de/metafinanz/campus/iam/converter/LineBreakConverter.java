package de.metafinanz.campus.iam.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("brConverter")
public class LineBreakConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		String val = (String) value;
		val = val.replaceAll("<br />", "\n");
		return val;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String val = (String) value;
		val = val.replaceAll("\n", "<br />");
		return val;
	}
}
