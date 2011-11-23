package org.mc.client.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mc.client.client.command.DialogCommand;
import org.mc.client.client.object.GuiAccessPoint;
import org.mc.client.client.object.GuiContainer;
import org.mc.client.client.object.Mart;
import org.mc.client.client.object.SourceContainer;
import org.mc.client.client.widget.MartButton;
import org.mc.client.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwttest implements EntryPoint {
	
	private String username = "yongliang";
	private FlowPanel mainPanel;
	private SplitLayoutPanel splitPanel;
	private VerticalPanel sourceGroupPanel;
	private FlowPanel portalPanel;
	//get all source containers
	private final List<SourceContainer> sclist = new ArrayList<SourceContainer>();
	private final List<Mart> martlist = new ArrayList<Mart>();
	//FIXME
	private boolean scReady;
	private boolean martsReady;
		 
	private Command command2 = new Command() {
	   public void execute() {
		   final DialogBox dbox = new DialogBox();
		   VerticalPanel dialogBoxContents = new VerticalPanel();
		   HorizontalPanel hpanel = new HorizontalPanel();
		   
		   final Button select = new Button("Select");
		   final Button cancel = new Button("Cancel");
		   final Label messageLabel = new Label();
		   messageLabel.setText("Query in progress ...");

		   final ListBox listbox = new ListBox(false);
		   listbox.setWidth("100%");
		   ConfiguratorServiceAsync stockPriceSvc = GWT.create(ConfiguratorService.class);
		   AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>() {
			      public void onFailure(Throwable caught) {
			    	  System.out.println("error");
			    	  cancel.setEnabled(true);
			    	  messageLabel.setText("Query error");
			      }

			      public void onSuccess(Collection<String> result) {
			    	  for(String registry: result) {
			    		 listbox.addItem(registry);
			    	  }
			    	  select.setEnabled(true);
			    	  cancel.setEnabled(true);
			    	  messageLabel.setText("Select Registry:");
			      }
			    };
			    stockPriceSvc.getAllRegistrysForUser(username, callback);
	   
		   listbox.setVisibleItemCount(10);
		   
		   select.addClickHandler(new ClickHandler() {
			   @Override
				public void onClick(ClickEvent event) {
				    dbox.hide();
				    updateSourceContainers(listbox.getValue(listbox.getSelectedIndex()));
				}
		   });
		   
		   
		   cancel.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					dbox.hide();
				}
		   });
		   
		   select.setEnabled(false);
		   cancel.setEnabled(false);
		   hpanel.add(select);
		   hpanel.add(cancel);
		   
		   dialogBoxContents.add(messageLabel);
		   dialogBoxContents.add(listbox);
		   dialogBoxContents.add(hpanel);
		   
		   dbox.setWidget(dialogBoxContents);
		   dbox.center();
	   }
	};
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {		
		DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
		this.mainPanel = new FlowPanel();
		this.mainPanel.add(this.getMainPanel());
		dockPanel.addNorth(this.createMenu(), 2);	
		dockPanel.add(this.mainPanel);
		RootLayoutPanel.get().add(dockPanel);
	}
	
	private Widget getMainPanel() {
		DisclosurePanel defaultSource = this.getDefaultSourceGroupPanel();	
		sourceGroupPanel = new VerticalPanel();
		sourceGroupPanel.setStyleName("source-panel");
		sourceGroupPanel.setWidth("100%");
		sourceGroupPanel.add(defaultSource);
		
		//dockPanel.addWest(sourceGroupPanel, 20);
		portalPanel = new FlowPanel();
		portalPanel.setWidth("100%");
		TabLayoutPanel tab = this.getDefaultPortalPanel();
		portalPanel.add(tab);
		
		splitPanel = new SplitLayoutPanel(5);
		splitPanel.setWidth("100%");
		splitPanel.setHeight("100%");
		splitPanel.getElement().getStyle().setProperty("border", "3px solid #e7e7e7");
		
		splitPanel.addWest(sourceGroupPanel, 300);
		splitPanel.add(portalPanel);
		
		return splitPanel;
	}
	
	private MenuBar createMenu() {
		MenuBar menutop = new MenuBar();
		MenuBar fileMenu = new MenuBar(true);
		MenuItem itemNew = new MenuItem("New", new DialogCommand());
		itemNew.setEnabled(false);
		MenuItem itemOpen = new MenuItem("Open Registry", command2);

		menutop.addItem("File", fileMenu); // Creates item and adds menutwo
		fileMenu.addItem(itemNew);
		fileMenu.addItem(itemOpen);
		return menutop;
	}
	
	private void updateSourceContainers(String registry) {
		this.setMartsReadyFlag(false);
		this.setScReadyFlag(false);
		ConfiguratorServiceAsync service = GWT.create(ConfiguratorService.class);
		AsyncCallback<Collection<SourceContainer>> callback = new AsyncCallback<Collection<SourceContainer>>() {
			      public void onFailure(Throwable caught) {
			    	  System.out.println("t");
			      }

			      public void onSuccess(Collection<SourceContainer> result) {
			    	  sourceGroupPanel.clear();
			    	  for(SourceContainer sc: result) {
			    		  sclist.add(sc);
			    	  }
			    	  setScReadyFlag(true);
			    	  resetSourceGroupPanel();
			      }
		};
		service.getSourceContainers(username, registry, callback);
		//get all marts
		AsyncCallback<Collection<Mart>> martcallback = new AsyncCallback<Collection<Mart>>() {
		      public void onFailure(Throwable caught) {
		    	  System.out.println("t");
		      }

		      public void onSuccess(Collection<Mart> result) {
		    	  for(Mart sc: result) {
		    		  martlist.add(sc);
		    	  }
		    	  setMartsReadyFlag(true);
		    	  resetSourceGroupPanel();
		      }
		};
		service.getMarts(username, registry, martcallback);
		//refresh portal
		AsyncCallback<GuiContainer> gccallback = new AsyncCallback<GuiContainer>() {
		      public void onFailure(Throwable caught) {
		    	  System.out.println("t");
		      }

		      public void onSuccess(GuiContainer result) {
		    	  resetPortalPanel(result);
		      }
		};
		service.getRootGuiContainer(username, registry, gccallback);
			
		
	}
	
	private void updatePortalPanel() {
		
	}
	
	private void setScReadyFlag(boolean b) {
		this.scReady = b;
	}
	
	private void setMartsReadyFlag(boolean b) {
		this.martsReady = b;
	}
	
	private void resetSourceGroupPanel() {
		Map<String,DisclosurePanel> scmap = new HashMap<String,DisclosurePanel>();
		if(scReady && martsReady) {
			sourceGroupPanel.clear();
			//add source container
			for(SourceContainer sc: this.sclist) {
				DisclosurePanel dp = new DisclosurePanel(sc.getDisplayname());
				dp.setWidth("100%");
				dp.setOpen(true);
				VerticalPanel vp = new VerticalPanel();
				vp.setStyleName("source-panel");

				Button add = new Button("add source");
	    		  add.setEnabled(false);

	    		  vp.add(add);
	    		  dp.add(vp);
	    		  sourceGroupPanel.add(dp);
	    		  scmap.put(sc.getName(), dp);
			}
			
			for(Mart mart: this.martlist) {
	    		  String scname = mart.getSourcecontainer();
	    		  if(scname.isEmpty())
	    			  scname = "default";
	    		  DisclosurePanel dp = scmap.get(scname);
	    		  if(dp==null) 
	    			  dp = scmap.get("default");
	    		  
	    		  MartButton martbutton = new MartButton(mart.getName());
	    		  martbutton.setMart(mart);
	    		  martbutton.setStyleName("mart-button");
	    		  VerticalPanel vp = (VerticalPanel)dp.getContent();
	    		  vp.add(martbutton);
			}
		}
	}
	
	private void resetPortalPanel(GuiContainer rootgc) {
		this.portalPanel.clear();
		TabLayoutPanel tab = new TabLayoutPanel(1.5, Unit.EM);
		tab.setWidth("100%");
		tab.setHeight("100%");
		for(GuiContainer subgc: rootgc.getGuiContainers()) {
			tab.add(this.getWidgetFromGuiContainer(subgc),subgc.getDisplayname());			
		}
		this.portalPanel.add(tab);
	}
	
	private Widget getWidgetFromGuiContainer(GuiContainer gc) {
		if(gc.getGuiContainers().isEmpty()) {
			VerticalPanel gcp = new VerticalPanel();
			gcp.setWidth("100%");
			
			final CellTable<GuiAccessPoint> table = this.getTable(new ArrayList<GuiAccessPoint>());
		    gcp.add(table);
		    //get accesspoint
			ConfiguratorServiceAsync service = GWT.create(ConfiguratorService.class);
			AsyncCallback<Collection<GuiAccessPoint>> callback = new AsyncCallback<Collection<GuiAccessPoint>>() {
				      public void onFailure(Throwable caught) {
				    	  System.out.println("t");
				      }

				      public void onSuccess(Collection<GuiAccessPoint> result) {
				    	  List<GuiAccessPoint> data = new ArrayList<GuiAccessPoint>(result);
				    	  table.setRowData(data);
				      }
			};
			service.getGuiAccessPoints(gc, callback);
			return gcp;

		} else {
			TabLayoutPanel tab = new TabLayoutPanel(1.5, Unit.EM);
			tab.setWidth("100%");
			tab.setHeight("100%");
			for(GuiContainer subgc: gc.getGuiContainers()) {
				tab.add(this.getWidgetFromGuiContainer(subgc));
			}
			return tab;
		}
	}
	
	private void showConfig() {
		this.mainPanel.clear();
		this.mainPanel.add(this.getConfigPanel());
	}
	
	private DisclosurePanel getDefaultSourceGroupPanel() {
		DisclosurePanel defaultSGP = new DisclosurePanel("default");
		defaultSGP.setWidth("100%");
		defaultSGP.setOpen(true);
		Button add = new Button("add source");
		add.setEnabled(false);
		defaultSGP.setContent(add);
		return defaultSGP;
	}

	private TabLayoutPanel getDefaultPortalPanel() {
		TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
		p.setWidth("100%");
		p.setHeight("100%");
		VerticalPanel gcp = new VerticalPanel();
		gcp.setWidth("100%");
		
		CellTable<GuiAccessPoint> table = this.getTable(new ArrayList<GuiAccessPoint>());
	    gcp.add(table);
	    table.setLoadingIndicator(null);
	    
		p.add(gcp,"default");
	
		return p;

	}
	
	private CellTable<GuiAccessPoint> getTable(Collection<GuiAccessPoint> gaps) {
		CellTable<GuiAccessPoint> table = new CellTable<GuiAccessPoint>();
		table.setWidth("100%");
		TextColumn<GuiAccessPoint> apcolumn = new TextColumn<GuiAccessPoint>() {
			@Override
			public String getValue(GuiAccessPoint object) {
				return object.getAccesspoint();
			}			
		};
		
		TextColumn<GuiAccessPoint> sourcecolumn = new TextColumn<GuiAccessPoint>() {
			@Override
			public String getValue(GuiAccessPoint object) {
				return object.getMart();
			}			
		};
		table.addColumn(apcolumn,"Accesspoint");
		table.addColumn(sourcecolumn,"Source");
		return table;
	}
	
	private Widget getConfigPanel() {
		SplitLayoutPanel configPanel = new SplitLayoutPanel(5);
		configPanel.setWidth("100%");
		configPanel.setHeight("100%");
		//get config
		//hardcode for now
		
		Tree targetTree = new Tree();
		targetTree.setWidth("100%");
		targetTree.setHeight("100%");
		TreeItem rootItem = new TreeItem("gene_vega");
		rootItem.addItem("test");
		rootItem.addItem("test");

		rootItem.addItem("test");
		targetTree.addItem(rootItem);
		
		//create source 		
		SplitLayoutPanel sourcePanel = new SplitLayoutPanel(5);
		SplitLayoutPanel targetPanel = new SplitLayoutPanel(5);
		
		configPanel.addWest(sourcePanel, 300);
		configPanel.add(targetPanel);
		
		sourcePanel.addNorth(new HTML("test"), 300);
		targetPanel.addNorth(targetTree, 300);
		return configPanel;
	}
	
	
}
