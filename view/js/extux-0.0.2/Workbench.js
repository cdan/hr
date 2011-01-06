
Ext.ux.Workbench = function(security) {
    this.map = {};
    this.security = security;
};

Ext.ux.Workbench.prototype = {
    init: function() {
        this.initView();
        this.initLoginWindow();
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

        setTimeout(function(){
            Ext.get('loading').remove();
            Ext.get('loading-mask').fadeOut({remove:true});
        }, 500);
    },

    initLoginWindow: function() {
        if (this.security.loginUrl) {
            this.loginWindow = new Ext.ux.LoginWindow({
                url: this.security.loginUrl,
                callback: function(result) {
                    this.rebuildMenu(result.info.menus);
                    this.storeTags(result.info.info);
                    this.loginWindow.hide();
                }.createDelegate(this)
            });


            Ext.Ajax.request({
                url: this.security.checkUrl,
                success: function(response) {
                    var result = Ext.decode(response.responseText);
                    if (result.success) {
                        this.rebuildMenu(result.info.menus);
                        this.storeTags(result.info.tags);
                    } else {
                        this.loginWindow.show();
                    }
                },
                failure: function(response) {
                    Ext.Msg.alert('错误', '无法访问服务器。');
                },
                scope: this
            });
        } else {
            var authLoginUrl = (typeof WEB_ROOT == 'undefined' ? '.' : WEB_ROOT) + '/extux/mockLogin!login.do';
            if (typeof AUTH_LOGIN_URL != 'undefined' && AUTH_LOGIN_URL && AUTH_LOGIN_URL != '') {
                authLoginUrl = AUTH_LOGIN_URL;
            }
            this.loginWindow = new Ext.ux.LoginWindow({
                url: authLoginUrl,
                callback: function(result) {
                    this.rebuildMenu(this.security);
                    this.loginWindow.hide();
                }.createDelegate(this)
            });

            var authCheckUrl = (typeof WEB_ROOT == 'undefined' ? '.' : WEB_ROOT) + '/extux/mockLogin!login.do';
            if (typeof AUTH_CHECK_URL != 'undefined' && AUTH_CHECK_URL && AUTH_CHECK_URL != '') {
                authCheckUrl = AUTH_CHECK_URL;
            }
            Ext.Ajax.request({
                url: authCheckUrl,
                success: function(response) {
                    var result = Ext.decode(response.responseText);
                    if (result.success) {
                        this.rebuildMenu(this.security);
                    } else {
                        this.loginWindow.show();
                    }
                },
                failure: function(response) {
                    Ext.Msg.alert('错误', '无法访问服务器。');
                },
                scope: this
            });
        }
    },

    logout: function() {
        if (this.security.logoutUrl) {
            Ext.Ajax.request({
                url: this.security.logoutUrl,
                success: function(response) {
                    this.clean();
                    this.loginWindow.formPanel.getForm().reset();
                    this.loginWindow.show();
                },
                failure: function(response) {
                    Ext.Msg.alert('错误', '无法访问服务器。');
                },
                scope: this
            });
        } else {
            var authLogoutUrl = (typeof WEB_ROOT == 'undefined' ? '.' : WEB_ROOT) + '/extux/mockLogin!logout.do';
            if (typeof AUTH_LOGOUT_URL != 'undefined' && AUTH_LOGOUT_URL && AUTH_LOGOUT_URL != '') {
                authLogoutUrl = AUTH_LOGOUT_URL;
            }
            Ext.Ajax.request({
                url: authLogoutUrl,
                success: function(response) {
                    this.clean();
                    this.loginWindow.formPanel.getForm().reset();
                    this.loginWindow.show();
                },
                failure: function(response) {
                    Ext.Msg.alert('错误', '无法访问服务器。');
                },
                scope: this
            });
        }
    },

    createNorthPanel: function() {
        var html = '<img src="' + (typeof WEB_ROOT == 'undefined' ? '.' : WEB_ROOT) + '/static/widgets/extux-0.0.2/family168.png">';
        var isDemoMode = typeof App != 'undefined' && App.MODE_DEMO === true;
        return {
            region: 'north',
            height: isDemoMode ? 0 : 80,
            margins: '5 5 5 5',
            html: isDemoMode ? '' : html,
            bbar: new Ext.Toolbar(['->', {
                text: 'Logout',
                iconCls: 'logout-btn',
                handler: function() {
                    this.logout();
                },
                scope: this
            }])
        };
    },

    createSouthPanel: function() {
        return {
            region: 'south',
            height: 18,
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
            width: 150,
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
        var isDemoMode = typeof App != 'undefined' && App.MODE_DEMO === true;
        var root = (typeof WEB_ROOT == 'undefined' ? '.' : WEB_ROOT) + '/static/widgets/extux-0.0.2';
        var html = '<table width="100%" height="100%"><tr><td align="center">'
            + '<a href="http://www.china-pub.com/195152" target="_blank">'
            + '<img src="' + root + '/dive1.jpg" width="300" height="380" border="0" /></a>'
            + '<a href="http://www.china-pub.com/193076" target="_blank">'
            + '<img src="' + root + '/advance.jpg" width="300" height="380" border="0" /></a>'
            + '<a href="http://www.china-pub.com/196661" target="_blank">'
            + '<img src="' + root + '/dive2.jpg" width="300" height="380" border="0" /></a>'
            + '</a></td></tr></table>'

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
                html: isDemoMode ? '' : html
            }]
        });

        return tabPanel;
    },

    register: function(name, fn, iconCls, notConstructor) {
        if (fn !== null && typeof fn !== 'function') {
            console.error(fn + ' must be a function for ' + name + '.');
        }
        App.view.map[name] = {
            fn: fn,
            iconCls: iconCls,
            notConstructor: notConstructor
        };
    },

    openTab: function(name) {
        var comp = this.map[name];
        if (typeof comp === 'undefined') {
            return;
        }
        if (comp.notConstructor === true) {
            comp.fn.call(this);
        } else {
            var tabs = this.center;
            var tabItem = tabs.getItem(name);
            if (tabItem == null) {
                var c = comp.fn;
                var p = new c;
                tabItem = tabs.add(p);
            }
            tabs.activate(tabItem);
        }
    },

    rebuildMenu: function(info) {
        var mainAccordion = this.west;
        for (var i = 0; i < info.length; i++) {
            var title = info[i].text;
            var comp = this.map[info[i].content];
            if (typeof comp === 'undefined') {
                continue;
            }
            var iconCls = comp.iconCls;

            for (var j = 0; j < info[i].children.length; j++) {
                var item = info[i].children[j];
                var child = this.map[item.content];
                if (typeof child !== 'undefined') {
                    item.iconCls = child.iconCls;
                }
            }

            var p = new Ext.tree.TreePanel({
                title: title,
                iconCls: iconCls,
                rootVisible: false,
                loader: new Ext.tree.TreeLoader(),
                root: new Ext.tree.AsyncTreeNode({
                    text:'功能菜单',
                    children: info[i].children
                })
            });
            p.on('click', function(node) {
                this.openTab(node.attributes.content);
            }, this);
            mainAccordion.add(p);
        }
        mainAccordion.doLayout();
    },

    storeTags: function(tags) {
        if (tags) {
            try {
                AUTH_TAGS = tags;
            } catch(e) {
                if (console) {
                    console.info(e);
                }
            }
        }
    },

    clean: function() {
        var tabs = this.center;
        for (var i = tabs.items.length; i > 0; i--) {
            var tab = tabs.getComponent(i);
            tabs.remove(tab, true);
        }
        this.west.removeAll();
    },

    notExists: function(id) {
        var tabItem = this.center.getItem(id);
        if (tabItem) {
            this.center.activate(tabItem);
            return false;
        } else {
            return true;
        }
    },

    openPanel: function(p) {
        var tabItem = this.center.add(p);
        this.center.activate(tabItem);
    },

    getPanel: function(id) {
        return this.center.getItem(id);
    },

    addPanel: function(conf) {
        var tabItem = this.center.getItem(conf.id);
        if (tabItem) {
            tabItem = new conf.fn;
            this.center.add(tabItem);
        }
        conf.callback.call(window, tabItem);
        this.center.activate(tabItem);
    }
};
