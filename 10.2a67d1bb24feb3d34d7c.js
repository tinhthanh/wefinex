(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{yl8Q:function(t,e,n){"use strict";n.r(e),n.d(e,"TabModule",function(){return f});var o=n("ofXK"),i=n("tyNb"),s=n("mrSG"),c=n("20jh"),a=n("QqCr"),r=n("lJxs"),u=n("fXoL"),l=function(){function t(t){this.tabId=t}return t.prototype.onClick=function(){return Object(s.b)(this,void 0,void 0,function(){var t;return Object(s.d)(this,function(e){switch(e.label){case 0:return console.log("dmeo"),t=this,[4,Object(a.a)(chrome.cookies.getAll.bind(this,{}))().pipe(Object(r.a)(function(t){return console.log(t.filter(function(t){return".facebook.com"===t.domain})),t})).toPromise()];case 1:return t.message=e.sent(),[2]}})})},t.prototype.addCookies=function(){[{domain:".facebook.com",expirationDate:1672942551.268056,hostOnly:!1,httpOnly:!0,name:"datr",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"1az0Xyp6NVg4eX9KXfYhhGDo"},{domain:".facebook.com",expirationDate:1672942570.955196,hostOnly:!1,httpOnly:!0,name:"sb",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"56z0X7QgwySol_mQrpe4li0b"},{domain:".facebook.com",expirationDate:1610475368,hostOnly:!1,httpOnly:!1,name:"wd",path:"/",sameSite:"lax",secure:!0,session:!1,storeId:"0",value:"427x757"},{domain:".facebook.com",expirationDate:1641406568.955329,hostOnly:!1,httpOnly:!1,name:"c_user",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"100005036221206"},{domain:".facebook.com",expirationDate:1641406568.955413,hostOnly:!1,httpOnly:!0,name:"xs",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"16%3AvywwFgVa2DcF6Q%3A2%3A1608728229%3A2820%3A6911%3A%3AAcXRhN16adgOwk8X4M-ehAe_IxpWKGXd0hVSZlAK4jGM"},{domain:".facebook.com",expirationDate:1617646566.95548,hostOnly:!1,httpOnly:!0,name:"fr",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"16F94TYUYsNkxKl2n.AWXUu1sSve6CxnBWnGAlCYAF658.Bf9Kzn.rI.AAA.0.0.Bf9Kzp.AWWs9_nfunI"},{domain:".facebook.com",expirationDate:1609960573.457867,hostOnly:!1,httpOnly:!0,name:"spin",path:"/",sameSite:"no_restriction",secure:!0,session:!1,storeId:"0",value:"r.1003151454_b.trunk_t.1609870573_s.1_v.2_"}].map(function(t){return new p(t,"https://www.facebook.com")}).forEach(function(t){chrome.cookies.set(t,function(t){console.log(t)})})},t.\u0275fac=function(e){return new(e||t)(u.yc(c.a))},t.\u0275cmp=u.sc({type:t,selectors:[["app-tab"]],decls:4,vars:0,consts:[[2,"text-align","center",3,"click"]],template:function(t,e){1&t&&(u.Ec(0,"button",0),u.Lc("click",function(){return e.onClick()}),u.td(1,"Tab"),u.Dc(),u.Ec(2,"button",0),u.Lc("click",function(){return e.addCookies()}),u.td(3,"You opened a new tab!"),u.Dc())},styles:[""]}),t}(),p=function(){return function(t,e){this.domain=t.domain,this.expirationDate=t.expirationDate,this.httpOnly=t.hostOnly,this.name=t.name,this.path=t.path,this.secure=t.secure,this.storeId=t.storeId,this.value=t.value,this.url=e}}(),h=[{path:"",component:l}],m=function(){function t(){}return t.\u0275mod=u.wc({type:t}),t.\u0275inj=u.vc({factory:function(e){return new(e||t)},imports:[[i.g.forChild(h)],i.g]}),t}(),f=function(){function t(){}return t.\u0275mod=u.wc({type:t}),t.\u0275inj=u.vc({factory:function(e){return new(e||t)},imports:[[o.c,m]]}),t}()}}]);