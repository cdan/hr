Ext.ux.Workbench = function() {
};

Ext.ux.Workbench.prototype = {
    init: function() {
        this.initView();
    },
    initView: function() {
        this.north = this.createNorthPanel();
        this.south = this.createSouthPanel();
        this.west = this.createWestPanel();
        this.center = this.createCenterPanel();

        var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
                this.north,
                this.south,
                this.west,
                this.center
            ]
        });
    },
    getMainAccordion:function(){
    	return this.west;
    },
    createNorthPanel: function() {
        var html = '<iframe id="frame_menu" src="layout/menu.seam" frameborder="0" width="100%" height="100%"></iframe>';
        //var html = ' <img src="/xcaib/img/xcaib.jpg" width="85%" height="80" /> ';
        //var html = ' <h:graphicImage value="../img/xcaib.jpg" styleClass="pic" width="85%" height="90"/> ';
        var isDemoMode = typeof App != 'undefined' && App.MODE_DEMO === true;
        return {
            region: 'north',
            //height: isDemoMode ? 0 : 40,
            //autoHeight: true,
            height: 118,
            margins: '0 0 0 0',
            html:html,
            split: false,
            collapsible: false,
            //autoScroll:false,
//            bbar: new Ext.Toolbar(['->', {
//                text: 'Logout',
//                iconCls: 'logout-btn',
//                handler: function() {
//                    this.logout();
//                },
//                scope: this
//            }])
        };
    },

    createSouthPanel: function() {
//        var html = '<iframe id="frame_menu" src="layout/menu.seam" frameborder="0" width="100%" height="100%"></iframe>';
//        //var html = ' <img src="/xcaib/img/xcaib.jpg" width="85%" height="80" /> ';
//        //var html = ' <h:graphicImage value="../img/xcaib.jpg" styleClass="pic" width="85%" height="90"/> ';
//        //var isDemoMode = typeof App != 'undefined' && App.MODE_DEMO === true;
//        return {
//            region: 'south',
//            //height: isDemoMode ? 0 : 40,
//            height: 25,
//            margins: '0 0 0 0',
//            html:html,
//            split: false,
//            collapsible: false,
////            bbar: new Ext.Toolbar(['->', {
////                text: 'Logout',
////                iconCls: 'logout-btn',
////                handler: function() {
////                    this.logout();
////                },
////                scope: this
////            }])
//        };
        return {
            region: 'south',
            height: 1,
            margins: '5 0 0 0',
            border: false,
            frame: false,
            bodyStyle: 'background-color:#99BBEE;color:white;font-size:12px;font-weight:bold;',
            html: new Date().toString()
        };
    },

    createWestPanel: function() {
        var westPanel = new Ext.Panel({
            id: 'mainAccordion',
            region: 'west',
            title: '功能菜单',
            layout: 'accordion',
            cls: 'west-menu',
            width: 200,
            minSize: 120,
            maxSize: 200,
            split: true,
            collapsible: true,
            margins: '0 0 0 5',
            cmargins: '0 5 0 5',
            frame: true,
            border: true,
            defaults: {
                border: false,
                lines: false,
                autoScroll: true,
                bodyStyle: 'background: #fff;',
                collapseFirst: true
            }
        });
        return westPanel;
    },

    createCenterPanel: function() {
        var tabPanel = new Ext.TabPanel({
            region: 'center',
            layoutOnTabChange: true,
            enableTabScroll: true,
            monitorResize: true,
            activeTab: 0,
            margins: '0 5 0 0',
            plain: true,
            frame: true,
            hideMode: 'offsets',
            deferredRender: false,
            deferredLayout: false,
            defaults: {
                closable: true,
                viewConfig: {
                    forceFit: true
                },
                margins: '5 5 5 5',
                bodyBorder: true
            },
            items: [{
                id: 'Home',
                title: '欢迎您',
                closable: false,
                autoScroll: true,
                iconCls: 'welcome',
                fitToFrame: true,                  
                html: '<iframe id="frame_home" src="welcome.seam" frameborder="0" width="100%" height="100%"></iframe>'  
            }]
        });

        return tabPanel;
    },
    openTab: function(id,text,url) {
    	var tabs = this.center;
        var tabItem = tabs.getItem("tab_"+id);
        var frameId = "frame_"+id;
    	if(tabItem === undefined){
    		tabItem = new Ext.Panel({
    			title:text,
                id:"tab_"+id,
                fitToFrame: true,                  
                html: '<iframe id="'+frameId+'" src="'+url+'" frameborder="0" width="100%" height="100%"></iframe>'  
    		});
    		tabs.add(tabItem);
    	}else{
    		document.getElementById(frameId).src = url;
    	}
    	tabs.activate(tabItem);	
    }
};

