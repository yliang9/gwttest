package org.mc.client.client.widget;

import org.mc.client.client.object.McConfig;
import org.mc.client.client.object.McLiteObject;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class McLiteObjectCell extends AbstractCell<McLiteObject> {

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			McLiteObject value, SafeHtmlBuilder sb) {
		if(value==null)
			return;
		SafeHtml safeValue = SafeHtmlUtils.fromString(value.getDisplayname());
		sb.append(safeValue);
	}
	
}