package org.mc.client.client.widget;

import org.mc.client.client.object.Containable;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class ContainableCell extends AbstractCell<Containable> {

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Containable value, SafeHtmlBuilder sb) {
		if(value==null)
			return;
		SafeHtml safeValue = SafeHtmlUtils.fromString(value.getDisplayname());
		sb.append(safeValue);
		
	}
	
}