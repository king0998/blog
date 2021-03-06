/* Copyright 2017 © Adobe Systems */
/*{"k":"1.1.13","auto_updating":true,"last_published":"2017-04-13 07:44:01 UTC"}*/
(function (config) {
    (function () {
        'use strict';
        var f, g = [];

        function l(a) {
            g.push(a);
            1 == g.length && f()
        }

        function m() {
            for (; g.length;)g[0](), g.shift()
        }

        f = function () {
            setTimeout(m)
        };
        function n(a) {
            this.a = p;
            this.b = void 0;
            this.f = [];
            var b = this;
            try {
                a(function (a) {
                    q(b, a)
                }, function (a) {
                    r(b, a)
                })
            } catch (c) {
                r(b, c)
            }
        }

        var p = 2;

        function t(a) {
            return new n(function (b, c) {
                c(a)
            })
        }

        function u(a) {
            return new n(function (b) {
                b(a)
            })
        }

        function q(a, b) {
            if (a.a == p) {
                if (b == a)throw new TypeError;
                var c = !1;
                try {
                    var d = b && b.then;
                    if (null != b && "object" == typeof b && "function" == typeof d) {
                        d.call(b, function (b) {
                            c || q(a, b);
                            c = !0
                        }, function (b) {
                            c || r(a, b);
                            c = !0
                        });
                        return
                    }
                } catch (e) {
                    c || r(a, e);
                    return
                }
                a.a = 0;
                a.b = b;
                v(a)
            }
        }

        function r(a, b) {
            if (a.a == p) {
                if (b == a)throw new TypeError;
                a.a = 1;
                a.b = b;
                v(a)
            }
        }

        function v(a) {
            l(function () {
                if (a.a != p)for (; a.f.length;) {
                    var b = a.f.shift(), c = b[0], d = b[1], e = b[2], b = b[3];
                    try {
                        0 == a.a ? "function" == typeof c ? e(c.call(void 0, a.b)) : e(a.b) : 1 == a.a && ("function" == typeof d ? e(d.call(void 0, a.b)) : b(a.b))
                    } catch (h) {
                        b(h)
                    }
                }
            })
        }

        n.prototype.g = function (a) {
            return this.c(void 0, a)
        };
        n.prototype.c = function (a, b) {
            var c = this;
            return new n(function (d, e) {
                c.f.push([a, b, d, e]);
                v(c)
            })
        };
        function w(a) {
            return new n(function (b, c) {
                function d(c) {
                    return function (d) {
                        h[c] = d;
                        e += 1;
                        e == a.length && b(h)
                    }
                }

                var e = 0, h = [];
                0 == a.length && b(h);
                for (var k = 0; k < a.length; k += 1)u(a[k]).c(d(k), c)
            })
        }

        function x(a) {
            return new n(function (b, c) {
                for (var d = 0; d < a.length; d += 1)u(a[d]).c(b, c)
            })
        };
        window.Promise || (window.Promise = n, window.Promise.resolve = u, window.Promise.reject = t, window.Promise.race = x, window.Promise.all = w, window.Promise.prototype.then = n.prototype.c, window.Promise.prototype["catch"] = n.prototype.g);
    }());

    (function () {
        function aa(a) {
            return a.map(function (a) {
                return "U+" + a.toString(16)
            }).join(",")
        }

        function ea(a) {
            a = a.split(/\s*,\s*/);
            for (var b = [], c = 0; c < a.length; c++) {
                var d = /^(u\+([0-9a-f?]{1,6})(?:-([0-9a-f]{1,6}))?)$/i.exec(a[c]), e;
                if (d)if (-1 !== d[2].indexOf("?") ? (e = parseInt(d[2].replace("?", "0"), 16), d = parseInt(d[2].replace("?", "f"), 16)) : (e = parseInt(d[2], 16), d = d[3] ? parseInt(d[3], 16) : e), e !== d)for (; e <= d; e++)b.push(e); else b.push(e)
            }
            return b
        };
        function m(a) {
            this.b = a || []
        }

        function r(a) {
            for (var b = {}, c = 0; c < a.b.length; c++)b[a.b[c]] = a.b[c];
            return Object.keys(b).map(function (a) {
                return b[a]
            }).sort(function (a, b) {
                return a - b
            })
        }

        function fa(a, b) {
            for (var c = {}, d = new m, e = 0; e < a.b.length; e++)c[a.b[e]] = !0;
            for (e = 0; e < b.b.length; e++)c[b.b[e]] || d.b.push(b.b[e]);
            return d
        }

        function x(a, b) {
            var c = new m;
            c.b = a.b.concat(b.b);
            return c
        };
        var y = document.createElement("div");

        function ga(a) {
            y.style.cssText = "font:" + a;
            if (y.style.fontFamily) {
                a:{
                    a = y.style.fontFamily;
                    for (var b = "", c = [], d = 0; d < a.length; d++) {
                        var e = a.charAt(d);
                        if ("'" === e || '"' === e) {
                            b = d + 1;
                            do if (b = a.indexOf(e, b) + 1, !b) {
                                a = null;
                                break a
                            } while ("\\" === a.charAt(b - 2));
                            c.push(a.slice(d + 1, b - 1));
                            d = b - 1;
                            b = ""
                        } else"," === e ? (b = b.trim(), "" !== b && (c.push(b), b = "")) : b += e
                    }
                    b = b.trim();
                    "" !== b && c.push(b);
                    a = c
                }
                if (a)return {
                    size: y.style.fontSize,
                    lineHeight: y.style.lineHeight || "normal",
                    style: y.style.fontStyle || "normal",
                    variant: y.style.fontVariant ||
                    "normal",
                    weight: y.style.fontWeight || "normal",
                    stretch: y.style.fontStretch || "normal",
                    family: a
                }
            }
            return null
        };
        function ha(a) {
            a = document.createTreeWalker(a, NodeFilter.SHOW_ELEMENT, null, !1);
            var b = [];
            do {
                var c = a.currentNode;
                if (c && "SCRIPT" !== c.nodeName && "STYLE" !== c.nodeName && "NOSCRIPT" !== c.nodeName && "TEMPLATE" !== c.nodeName && "LINK" !== c.nodeName && "TITLE" !== c.nodeName) {
                    for (var d = c.childNodes, e = 0; e < d.length; e++)d[e].nodeType !== Node.TEXT_NODE || /^\s*$/.test(d[e].nodeValue) || b.push(d[e].nodeValue);
                    "INPUT" === c.nodeName && "hidden" !== c.type && "password" !== c.type && b.push(c.value);
                    "TEXTAREA" === c.nodeName && b.push(c.value)
                }
            } while (a.nextNode());
            a = b.join("");
            b = new m;
            for (c = 0; c < a.length; c++)d = a.charCodeAt(c), 55296 === (d & 63488) && c < a.length ? (e = a.charCodeAt(c + 1), 56320 === (e & 64512) ? b.b.push(((d & 1023) << 10) + (e & 1023) + 65536) : b.b.push(d), c++) : b.b.push(d);
            return r(b)
        };
        function ia(a, b) {
            this.b = a;
            this.m = b;
            this.i = null;
            ja && (this.i = new MutationObserver(function (a) {
                for (var c = [], e = 0; e < a.length; e++)if (a[e].addedNodes.length || "characterData" === a[e].type || "attributes" === a[e].type) {
                    var f = a[e].target;
                    3 === f.nodeType && (f = f.parentNode);
                    f && c.push(f)
                }
                c.length && b(c)
            }))
        }

        var ja = !!window.MutationObserver;
        ia.prototype.g = function (a) {
            a.target && (a = a.target, 3 === a.nodeType && (a = a.parentNode), this.m([a]))
        };
        function ka(a) {
            ja ? a.i.observe(a.b, {
                    attributes: !0,
                    characterData: !0,
                    subtree: !0,
                    childList: !0
                }) : (a.b.addEventListener("DOMAttrModified", a.g.bind(a), !1), a.b.addEventListener("DOMNodeInserted", a.g.bind(a), !1), a.b.addEventListener("DOMCharacterDataModified", a.g.bind(a), !1))
        };
        function la(a) {
            var b = document.body, c = this;
            this.cache = {};
            this.b = new ia(b, function (b) {
                var d = [];
                b.forEach(function (a) {
                    ha(a).forEach(function (a) {
                        c.cache[a] || (d.push(a), c.cache[a] = !0)
                    })
                });
                d.length && a(d)
            })
        };
        function ma(a) {
            this.g = a || {};
            this.b = document.documentElement
        }

        ma.prototype.inactive = function () {
            z(this.b, "wf-loading");
            B(this.b, "wf-inactive");
            C(this, "inactive")
        };
        function na(a) {
            z(a.b, "wf-loading");
            B(a.b, "wf-active");
            C(a, "active")
        }

        ma.prototype.loading = function () {
            B(this.b, "wf-loading");
            C(this, "loading")
        };
        function oa(a, b) {
            z(a.b, D(b, "loading"));
            B(a.b, D(b, "inactive"));
            C(a, "fontinactive", b)
        }

        function pa(a, b) {
            B(a.b, D(b, "loading"));
            C(a, "fontloading", b)
        }

        function D(a, b) {
            return "wf-" + a.family + "-" + qa(a) + "-" + b
        }

        function C(a, b, c) {
            if (a.g[b])try {
                if (c) a.g[b](c.family, qa(c)); else a.g[b]()
            } catch (d) {
                console.error('Typekit: Error in "' + b + '" callback', d)
            }
        };
        function ra(a) {
            a = (a || "").split(/\s*,\s*/);
            for (var b = {}, c = 0; c < a.length; c++) {
                var d = /^"([\u0020-\u007e]{1,4})"(?:\s+(\d+|on|off))?$/i.exec(a[c]);
                if (d)if (d[2]) {
                    var e = d[2].replace("on", "1").replace("off", "0");
                    b[d[1]] = parseInt(e, 10)
                } else b[d[1]] = 1
            }
            return b
        };
        function sa(a) {
            this.b = a || {}
        }

        function ta(a) {
            var b = [];
            Object.keys(a.b).forEach(function (c) {
                a.b[c] && b.push(c)
            });
            return b
        };
        function ua(a) {
            this.b = a
        }

        function E(a, b) {
            return a.b.replace(/\{([^\{\}]+)\}/g, function (a, d) {
                if ("?" == d.charAt(0)) {
                    for (var c = d.slice(1).split(","), f = [], h = 0; h < c.length; h++)b[c[h]] && f.push(c[h] + "=" + encodeURIComponent(b[c[h]]));
                    return f.length ? "?" + f.join("&") : ""
                }
                return encodeURIComponent(b[d] || "")
            })
        };
        function B(a, b) {
            -1 === a.className.split(/\s+/).indexOf(b) && (a.className += " " + b)
        }

        function z(a, b) {
            if (-1 !== a.className.split(/\s+/).indexOf(b)) {
                var c = a.className.split(/\s+/);
                c.splice(c.indexOf(b), 1);
                a.className = c.join(" ")
            }
        }

        function va(a, b) {
            document.addEventListener ? a.addEventListener("scroll", b, !1) : a.attachEvent("scroll", b)
        }

        function wa(a) {
            document.body ? a() : document.addEventListener ? document.addEventListener("DOMContentLoaded", function c() {
                        document.removeEventListener("DOMContentLoaded", c);
                        a()
                    }) : document.attachEvent("onreadystatechange", function d() {
                        if ("interactive" == document.readyState || "complete" == document.readyState) document.detachEvent("onreadystatechange", d), a()
                    })
        };
        function xa(a) {
            this.b = document.createElement("div");
            this.b.setAttribute("aria-hidden", "true");
            this.b.appendChild(document.createTextNode(a));
            this.g = document.createElement("span");
            this.i = document.createElement("span");
            this.C = document.createElement("span");
            this.m = document.createElement("span");
            this.A = -1;
            this.g.style.cssText = "max-width:none;display:inline-block;position:absolute;height:100%;width:100%;overflow:scroll;font-size:16px;";
            this.i.style.cssText = "max-width:none;display:inline-block;position:absolute;height:100%;width:100%;overflow:scroll;font-size:16px;";
            this.m.style.cssText = "max-width:none;display:inline-block;position:absolute;height:100%;width:100%;overflow:scroll;font-size:16px;";
            this.C.style.cssText = "display:inline-block;width:200%;height:200%;font-size:16px;max-width:none;";
            this.g.appendChild(this.C);
            this.i.appendChild(this.m);
            this.b.appendChild(this.g);
            this.b.appendChild(this.i)
        }

        function F(a, b) {
            a.b.style.cssText = "max-width:none;min-width:20px;min-height:20px;display:inline-block;overflow:hidden;position:absolute;width:auto;margin:0;padding:0;top:-999px;left:-999px;white-space:nowrap;font:" + b + ";"
        }

        function ya(a) {
            var b = a.b.offsetWidth, c = b + 100;
            a.m.style.width = c + "px";
            a.i.scrollLeft = c;
            a.g.scrollLeft = a.g.scrollWidth + 100;
            return a.A !== b ? (a.A = b, !0) : !1
        }

        function za(a, b) {
            function c() {
                var a = d;
                ya(a) && a.b.parentNode && b(a.A)
            }

            var d = a;
            va(a.g, c);
            va(a.i, c);
            ya(a)
        };
        function Aa(a, b) {
            var c = b || {};
            this.family = a;
            this.style = c.style || "normal";
            this.weight = c.weight || "normal";
            this.stretch = c.stretch || "normal"
        }

        var Ba = null, Ca = null, Da = null;

        function Ea() {
            if (null === Ca) {
                var a = document.createElement("div");
                try {
                    a.style.font = "condensed 100px sans-serif"
                } catch (b) {
                }
                Ca = "" !== a.style.font
            }
            return Ca
        }

        function G(a, b) {
            return [a.style, a.weight, Ea() ? a.stretch : "", "100px", b].join(" ")
        }

        Aa.prototype.load = function (a, b) {
            var c = this, d = a || "BESbswy", e = 0, f = b || 3E3, h = (new Date).getTime();
            return new Promise(function (a, b) {
                null === Da && (Da = !!document.fonts);
                if (Da) {
                    var g = new Promise(function (a, b) {
                        function e() {
                            (new Date).getTime() - h >= f ? b() : document.fonts.load(G(c, '"' + c.family + '"'), d).then(function (b) {
                                    1 <= b.length ? a() : setTimeout(e, 25)
                                }, function () {
                                    b()
                                })
                        }

                        e()
                    }), l = new Promise(function (a, b) {
                        e = setTimeout(b, f)
                    });
                    Promise.race([l, g]).then(function () {
                        clearTimeout(e);
                        a(c)
                    }, function () {
                        b(c)
                    })
                } else wa(function () {
                    function g() {
                        var b;
                        if (b = -1 != q && -1 != u || -1 != q && -1 != k || -1 != u && -1 != k) (b = q != u && q != k && u != k) || (null === Ba && (b = /AppleWebKit\/([0-9]+)(?:\.([0-9]+))/.exec(window.navigator.userAgent), Ba = !!b && (536 > parseInt(b[1], 10) || 536 === parseInt(b[1], 10) && 11 >= parseInt(b[2], 10))), b = Ba && (q == t && u == t && k == t || q == ca && u == ca && k == ca || q == da && u == da && k == da)), b = !b;
                        b && (v.parentNode && v.parentNode.removeChild(v), clearTimeout(e), a(c))
                    }

                    function l() {
                        if ((new Date).getTime() - h >= f) v.parentNode && v.parentNode.removeChild(v), b(c); else {
                            var a = document.hidden;
                            if (!0 === a ||
                                void 0 === a) q = n.b.offsetWidth, u = p.b.offsetWidth, k = w.b.offsetWidth, g();
                            e = setTimeout(l, 50)
                        }
                    }

                    var n = new xa(d), p = new xa(d), w = new xa(d), q = -1, u = -1, k = -1, t = -1, ca = -1, da = -1, v = document.createElement("div");
                    v.dir = "ltr";
                    F(n, G(c, "sans-serif"));
                    F(p, G(c, "serif"));
                    F(w, G(c, "monospace"));
                    v.appendChild(n.b);
                    v.appendChild(p.b);
                    v.appendChild(w.b);
                    document.body.appendChild(v);
                    t = n.b.offsetWidth;
                    ca = p.b.offsetWidth;
                    da = w.b.offsetWidth;
                    l();
                    za(n, function (a) {
                        q = a;
                        g()
                    });
                    F(n, G(c, '"' + c.family + '",sans-serif'));
                    za(p, function (a) {
                        u = a;
                        g()
                    });
                    F(p, G(c, '"' + c.family + '",serif'));
                    za(w, function (a) {
                        k = a;
                        g()
                    });
                    F(w, G(c, '"' + c.family + '",monospace'))
                })
            })
        };
        var Fa = null;

        function Ga() {
            if (!Fa) {
                if (/MSIE|Trident/.test(navigator.userAgent))return Promise.resolve(["woff", "opentype", "truetype"]);
                var a = document.createElement("style"), b = document.getElementsByTagName("head")[0];
                a.appendChild(document.createTextNode('@font-face{font-family:"_fff_";src:url(data:font/woff2;base64,d09GMgABAAAAAADcAAoAAAAAAggAAACWAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAABk4ALAoUNAE2AiQDCAsGAAQgBSAHIBtvAciuMTaGVo8IaqBbcKPeB3CyAAIO4unr9nb72QE3p00iGQQIZcAAcAMEJOztBx7zdWVWn//BAPW1l0BN429cPrCPE75MA637gPs0DjavNxzHtWeXXErKIV3AF9TbHqCTOATL2BgjeIH30lQwSAonU1LabV8Iz12wDvgd/obV5QVxXDKvUhW1QfWNrS6HzEQJaP4tBA==) format("woff2"),url(data:application/font-woff;base64,d09GRgABAAAAAAHgAAoAAAAAAggAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABPUy8yAAABUAAAABcAAABOBIQEIWNtYXAAAAFwAAAAJgAAACwADABzZ2x5ZgAAAaAAAAAUAAAAFAwBPQJoZWFkAAAA9AAAAC0AAAA2CHEB92hoZWEAAAEkAAAAFgAAACQMAQgDaG10eAAAAWgAAAAIAAAACAgAAABsb2NhAAABmAAAAAYAAAAGAAoAAG1heHAAAAE8AAAAEwAAACAABAACbmFtZQAAAbQAAAAeAAAAIAAjCF5wb3N0AAAB1AAAAAwAAAAgAAMAAHgBY2BkYABhb81vuvH8Nl8ZmFgYQOBCWvVrMP3VURxEczBAxBmYQAQAAFIIBgAAAHgBY2BkYGBhAAEOKAkUQQVMAAJKABkAAHgBY2BkYGBgAkIgjQ0AAAC+AAcAeAFjAIEUBkYGcoECgwILmAEiASBRAK4AAAAAAAgAAAB4AWNgYGBkYAZiBgYeBhYGBSDNAoQgvsP//xDy/0EwnwEATX4GfAAAAAAAAAAKAAAAAQAAAAAIAAQAAAEAADEBCAAEAHgBY2BgYGKQY2BmYGThZGAEshmgbCYw2wEABjMAigAAeAFjYGbACwAAfQAE) format("woff")}'));
                b.appendChild(a);
                Fa = (new Aa("_fff_", {})).load("@", 5E3).then(function () {
                    var c = new xa("@"), d = ["opentype", "truetype"];
                    F(c, "_fff_");
                    document.body.appendChild(c.b);
                    var e = c.b.offsetWidth;
                    200 <= e && d.unshift("woff");
                    300 == e && d.unshift("woff2");
                    b.removeChild(a);
                    document.body.removeChild(c.b);
                    return d
                }, function () {
                    return ["opentype", "truetype"]
                })
            }
            return Fa
        };
        function Ha(a) {
            for (var b = /\burl\((\'|\"|)([^\'\"]+?)\1\)( format\((\'|\"|)([^\'\"]+?)\4\))?/g, c, d = []; c = b.exec(a);)c[2] && d.push({
                url: c[2],
                format: c[5]
            });
            return d
        };
        function Ia(a, b) {
            this.status = b.status;
            this.ok = 200 <= b.status && 300 > b.status || !b.status;
            this.statusText = b.statusText;
            this.body = a
        }

        Ia.prototype.arrayBuffer = function () {
            return Promise.resolve(this.body)
        };
        var Ja = !(window.XDomainRequest && !("responseType" in XMLHttpRequest.prototype));

        function Ka(a) {
            var b = {};
            return new Promise(function (c, d) {
                if (Ja) {
                    var e = new XMLHttpRequest;
                    e.onload = function () {
                        c(new Ia(e.response, {status: e.status, statusText: e.statusText}))
                    };
                    e.onerror = function () {
                        d(new TypeError("Network request failed"))
                    };
                    e.open("GET", a);
                    e.responseType = "arraybuffer";
                    b && Object.keys(b).forEach(function (a) {
                        e.setRequestHeader(a, b[a])
                    });
                    e.send(null)
                } else e = new XDomainRequest, e.open("GET", a.replace(/^http(s)?:/i, window.location.protocol)), e.ontimeout = function () {
                    return !0
                }, e.onprogress = function () {
                    return !0
                },
                    e.onload = function () {
                        c(new Ia(e.responseText, {status: e.status, statusText: e.statusText}))
                    }, e.onerror = function () {
                    d(new TypeError("Network request failed"))
                }, setTimeout(function () {
                    e.send(null)
                }, 0)
            })
        };
        function La(a, b, c) {
            var d = this, e = c || {};
            this.source = b;
            this.i = null;
            this.b = [];
            this.G = new Promise(function (a, b) {
                d.A = a;
                d.m = b
            });
            this.o = "unloaded";
            this.g = null;
            Object.defineProperties(this, {
                family: {
                    get: function () {
                        return a
                    }
                }, style: {
                    get: function () {
                        return e.style || "normal"
                    }
                }, weight: {
                    get: function () {
                        return e.weight || "normal"
                    }
                }, stretch: {
                    get: function () {
                        return e.stretch || "normal"
                    }
                }, unicodeRange: {
                    get: function () {
                        return e.unicodeRange || "U+0-10FFFF"
                    }
                }, variant: {
                    get: function () {
                        return e.variant || "normal"
                    }
                }, featureSettings: {
                    get: function () {
                        return e.featureSettings ||
                            "normal"
                    }
                }, status: {
                    get: function () {
                        return this.o
                    }
                }, loaded: {
                    get: function () {
                        return this.G
                    }
                }
            });
            "string" === typeof b ? this.b = Ha(b) : (this.i = b, this.o = "loaded", this.A(d))
        }

        var H = null;

        function Ma(a, b) {
            for (var c = null, d = 0; d < b.length; d++)for (var e = 0; e < a.b.length; e++)if (b[d] === a.b[e].format && null === c) {
                c = a.b[e].url;
                break
            }
            !c && b.length && (c = a.b[0].url);
            return c
        }

        La.prototype.load = function () {
            var a = this;
            "unloaded" === a.o && (a.o = "loading", Ga().then(function (b) {
                (b = Ma(a, b)) ? Ka(b).then(function (a) {
                        if (a.ok)return a.arrayBuffer();
                        throw a;
                    }).then(function (b) {
                        a.i = b;
                        a.o = "loaded";
                        a.A(a)
                    })["catch"](function () {
                        a.o = "error";
                        a.m(a)
                    }) : (a.o = "error", a.m(a))
            })["catch"](function () {
                a.o = "error";
                a.m(a)
            }));
            return this.G
        };
        function I() {
            this.fonts = [];
            this.o = "loaded";
            Object.defineProperties(this, {
                status: {
                    get: function () {
                        return this.o
                    }
                }, size: {
                    get: function () {
                        return this.fonts.length
                    }
                }
            })
        }

        I.prototype.add = function (a) {
            if (!this.has(a)) {
                H || (H = document.createElement("style"), document.head.appendChild(H));
                var b;
                if ("loaded" === a.o) {
                    b = new Uint8Array(a.i);
                    for (var c = "", d = 0; d < b.length; d++)c += String.fromCharCode(b[d]);
                    b = "url(data:font/opentype;base64," + btoa(c) + ")"
                } else b = a.source;
                H.sheet.insertRule('@font-face{font-family:"' + a.family + '";font-style:' + a.style + ";font-weight:" + a.weight + ";src:" + b + ";}", 0);
                a.g = H.sheet.cssRules[0];
                this.fonts.push(a)
            }
        };
        I.prototype["delete"] = function (a) {
            var b = this.fonts.indexOf(a);
            if (-1 !== b) {
                if (H && a.g)for (var c = 0; c < H.sheet.cssRules.length; c++)if (a.g === H.sheet.cssRules[c]) {
                    H.sheet.deleteRule(c);
                    a.g = null;
                    break
                }
                this.fonts.splice(b, 1);
                return !0
            }
            return !1
        };
        I.prototype.clear = function () {
            this.fonts = []
        };
        I.prototype.has = function (a) {
            return -1 !== this.fonts.indexOf(a)
        };
        I.prototype.forEach = function (a) {
            var b = this;
            this.fonts.forEach(function (c, d) {
                a(c, d, b)
            })
        };
        function Na(a, b) {
            function c(a) {
                return "bold" === a ? 700 : "normal" === a ? 400 : a
            }

            var d = ga(b);
            return d ? a.fonts.filter(function (a) {
                    for (var b = d.family, e = 0; e < b.length; e++)if (a.family === b[e] && a.style === d.style && a.stretch === d.stretch && c(a.weight) === c(d.weight))return !0;
                    return !1
                }) : null
        }

        I.prototype.load = function (a) {
            var b = this, c = Na(this, a);
            return c ? c.length ? (b.o = "loading", Promise.all(c.map(function (a) {
                        return a.load()
                    })).then(function () {
                        b.o = "loaded";
                        return c
                    })["catch"](function () {
                        b.o = "loaded";
                        return c
                    })) : Promise.resolve([]) : Promise.reject([])
        };
        I.prototype.check = function (a) {
            a = Na(this, a);
            if (a.length) {
                for (var b = 0; b < a.length; b++)if ("loaded" !== a[b].status)return !1;
                return !0
            }
            return !1
        };
        if (window.FontFace) J = window.FontFace, J.prototype.load = window.FontFace.prototype.load, K = document.fonts; else {
            var J = La;
            J.prototype.load = La.prototype.load;
            var K = new I
        }
        ;
        function L(a, b) {
            return (a & 65535) * b + (((a >>> 16) * b & 65535) << 16)
        }

        function Oa(a, b) {
            var c;
            c = L(a & 4294967295, 3432918353);
            c = L(c << 15 | c >>> 17, 461845907);
            c ^= b || 0;
            c = L(c << 13 | c >>> 19, 5) + 3864292196;
            c ^= 4;
            c = L(c ^ c >>> 16, 2246822507);
            c = L(c ^ c >>> 13, 3266489909);
            return (c ^ c >>> 16) >>> 0
        }

        function Pa(a, b) {
            var c = b || 0, d, e, f = a.length % 4, h = a.length - f;
            for (e = 0; e < h; e += 4)d = (a.charCodeAt(e + 0) & 4294967295) << 0 | (a.charCodeAt(e + 1) & 4294967295) << 8 | (a.charCodeAt(e + 2) & 4294967295) << 16 | (a.charCodeAt(e + 3) & 4294967295) << 24, d = L(d, 3432918353), d = d << 15 | d >>> 17, d = L(d, 461845907), c ^= d, c = c << 13 | c >>> 19, c = L(c, 5) + 3864292196;
            d = 0;
            switch (f) {
                case 3:
                    d ^= (a.charCodeAt(e + 2) & 4294967295) << 16;
                case 2:
                    d ^= (a.charCodeAt(e + 1) & 4294967295) << 8;
                case 1:
                    d ^= (a.charCodeAt(e + 0) & 4294967295) << 0, d = L(d, 3432918353), d = L(d << 15 | d >>> 17, 461845907), c ^=
                        d
            }
            c ^= a.length;
            c = L(c ^ c >>> 16, 2246822507);
            c = L(c ^ c >>> 13, 3266489909);
            return (c ^ c >>> 16) >>> 0
        };
        function Qa(a, b) {
            this.b = b || Array(Math.ceil(a / 32));
            this.size = a;
            if (!b)for (var c = 0; c < this.b.length; c++)this.b[c] = 0
        }

        Qa.prototype.set = function (a) {
            if (Math.floor(a / 32 + 1) > this.b.length)throw Error("Index is out of bounds.");
            var b = Math.floor(a / 32);
            this.b[b] |= 1 << a - 32 * b
        };
        Qa.prototype.has = function (a) {
            if (Math.floor(a / 32 + 1) > this.b.length)throw Error("Index is out of bounds.");
            var b = Math.floor(a / 32);
            return !!(this.b[b] & 1 << a - 32 * b)
        };
        function Ra(a, b, c) {
            this.size = a;
            this.g = b;
            this.b = new Qa(a, c)
        }

        var M = [2449897292, 4218179547, 2675077685, 1031960064, 1478620578, 1386343184, 3194259988, 2656050674, 3012733295, 2193273665];
        Ra.prototype.add = function (a) {
            if ("string" !== typeof a && "number" !== typeof a)throw Error("Value should be a string or number.");
            for (var b = "number" === typeof a, c = 0; c < this.g; c++)this.b.set(b ? Oa(a, M[c]) % this.size : Pa(a, M[c]) % this.size)
        };
        Ra.prototype.has = function (a) {
            if ("string" !== typeof a && "number" !== typeof a)throw Error("Value should be a string or number.");
            for (var b = "number" === typeof a, c = 0; c < this.g; c++)if (!this.b.has(b ? Oa(a, M[c]) % this.size : Pa(a, M[c]) % this.size))return !1;
            return !0
        };
        function Sa(a) {
            a = [a.size, a.g].concat(a.b.b);
            for (var b = "", c = 0; c < a.length; c++)var d = a[c], b = b + (String.fromCharCode((d & 4278190080) >>> 24) + String.fromCharCode((d & 16711680) >>> 16) + String.fromCharCode((d & 65280) >>> 8) + String.fromCharCode((d & 255) >>> 0));
            a = b;
            b = "";
            if (window.btoa) b = window.btoa(a); else for (var e, d = 0, f = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="; a.charAt(d | 0) || (f = "=", d % 1); b += f.charAt(63 & e >> 8 - d % 1 * 8)) {
                c = a.charCodeAt(d += .75);
                if (255 < c)throw Error("'btoa' failed: The string to be encoded contains characters outside of the Latin1 range.");
                e = e << 8 | c
            }
            return b.replace(/\+/g, "-").replace(/\//g, "_").replace(/=+$/, "")
        };
        function N(a, b, c) {
            this.unicode = a;
            this.g = b || [];
            this.b = c || null
        }

        N.prototype.get = function (a) {
            var b = Ta(this), c;
            c = "";
            if (this.b)for (var d = new Uint8Array(this.b.buffer, this.b.byteOffset, this.b.byteLength), e = 0; e < d.byteLength; e++)d[e] && (c += String.fromCharCode(d[e]));
            c = c.replace(/\+/g, "-").replace(/\//g, "_").replace(/=+$/, "");
            d = Ua(this);
            return "" !== c ? {format: a, unicode: b, gdyn: c, v: "3"} : {format: a, unicode: b, features: d, v: "3"}
        };
        function Ta(a) {
            var b = Math.min(Math.ceil(Math.log(.01) * (a.unicode.length || 1) / Math.log(1 / Math.pow(2, Math.log(2)))), 9586), c = new Ra(b, Math.max(Math.min(Math.round(Math.log(2) * b / (a.unicode.length || 1)), M.length), 1));
            a.unicode.forEach(function (a) {
                c.add(a)
            });
            return Sa(c)
        }

        function Ua(a) {
            return a.g.length ? a.g.map(function (a) {
                    return a.trim()
                }).join(",") : "NONE"
        };
        function Va() {
            this.g = [];
            this.b = [];
            var a = 0, b = 2, c;
            a:for (; 64 > a; b++) {
                for (c = 2; c * c <= b; c++)if (!(b % c))continue a;
                8 > a && (this.b[a] = Wa(Math.pow(b, .5)));
                this.g[a] = Wa(Math.pow(b, 1 / 3));
                a++
            }
        }

        function O(a, b) {
            return b >>> a | b << 32 - a
        }

        function Wa(a) {
            return 4294967296 * (a - Math.floor(a)) | 0
        }

        function P(a) {
            for (var b = "", c, d = 7; 0 <= d; d--)c = a >>> 4 * d & 15, b += c.toString(16);
            return b
        };
        var Xa = !(window.XDomainRequest && !("responseType" in XMLHttpRequest.prototype));

        function Q(a, b) {
            return new Promise(function (c, d) {
                var e = b || {method: "GET", headers: {}, body: null};
                if (Xa) {
                    var f = new XMLHttpRequest;
                    f.onload = function () {
                        c({body: f.response, status: f.status, statusText: f.statusText})
                    };
                    f.onerror = function () {
                        d(Error("Network request failed"))
                    };
                    f.open(e.method, a, !0);
                    f.responseType = "arraybuffer";
                    e.headers && Object.keys(e.headers).forEach(function (a) {
                        f.setRequestHeader(a, e.headers[a])
                    });
                    f.send(e.body)
                } else f = new XDomainRequest, f.open(e.method, a.replace(/^http(s)?:/i, window.location.protocol)),
                    f.ontimeout = function () {
                        return !0
                    }, f.onprogress = function () {
                    return !0
                }, f.onload = function () {
                    c({body: null, status: f.status, statusText: f.statusText})
                }, f.onerror = function () {
                    d(Error("Network request failed"))
                }, setTimeout(function () {
                    f.send(e.body)
                }, 0)
            })
        };
        function Ya(a, b, c) {
            this.unicode = a;
            this.i = b || [];
            this.b = c || null;
            this.g = null
        }

        var Za = {};

        function $a(a) {
            var b = ab(a);
            Za[b] || (Za[b] = Q("https://primer.typekit.net/primer/" + ab(a), {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: bb(a)
            }).then(function () {
                return b
            }));
            return Za[b]
        }

        function cb(a) {
            var b = "";
            a = new Uint8Array(a.b.buffer, a.b.byteOffset, a.b.byteLength);
            for (var c = 0; c < a.byteLength; c++)b += String.fromCharCode(a[c]);
            return btoa(b)
        }

        function db(a) {
            return a.i.length ? a.i.map(function (a) {
                    return a.trim()
                }).join(",") : "NONE"
        }

        function bb(a) {
            var b = "version=1.0&unicode=" + encodeURIComponent(a.unicode.join(","));
            return b = a.b ? b + ("&dyna=" + encodeURIComponent(cb(a))) : b + ("&features=" + encodeURIComponent(db(a)))
        }

        function ab(a) {
            if (null === a.g) {
                var b = {version: "1.0", unicode: a.unicode.join(",")};
                a.b ? b.dyna = cb(a) : b.features = db(a);
                for (var c = new Va, d = JSON.stringify(b), b = c.g.slice(0), c = c.b.slice(0), d = d + String.fromCharCode(128), e = Math.ceil((d.length / 4 + 2) / 16), f = Array(e), h = 0; h < e; h++) {
                    f[h] = Array(16);
                    for (var g = 0; 16 > g; g++)f[h][g] = d.charCodeAt(64 * h + 4 * g) << 24 | d.charCodeAt(64 * h + 4 * g + 1) << 16 | d.charCodeAt(64 * h + 4 * g + 2) << 8 | d.charCodeAt(64 * h + 4 * g + 3)
                }
                f[e - 1][14] = 8 * (d.length - 1) / Math.pow(2, 32);
                f[e - 1][14] = Math.floor(f[e - 1][14]);
                f[e - 1][15] =
                    8 * (d.length - 1) & 4294967295;
                for (var d = Array(64), l, k, t, ba, A, n, p, w, h = 0; h < e; h++) {
                    for (g = 0; 16 > g; g++)d[g] = f[h][g];
                    for (g = 16; 64 > g; g++)l = d[g - 15], k = d[g - 2], d[g] = (O(17, k) ^ O(19, k) ^ k >>> 10) + d[g - 7] + (O(7, l) ^ O(18, l) ^ l >>> 3) + d[g - 16] & 4294967295;
                    l = c[0];
                    k = c[1];
                    t = c[2];
                    ba = c[3];
                    A = c[4];
                    n = c[5];
                    p = c[6];
                    w = c[7];
                    for (g = 0; 64 > g; g++) {
                        var q = w + (O(6, A) ^ O(11, A) ^ O(25, A)) + (A & n ^ ~A & p) + b[g] + d[g], u = (O(2, l) ^ O(13, l) ^ O(22, l)) + (l & k ^ l & t ^ k & t);
                        w = p;
                        p = n;
                        n = A;
                        A = ba + q & 4294967295;
                        ba = t;
                        t = k;
                        k = l;
                        l = q + u & 4294967295
                    }
                    c[0] = c[0] + l & 4294967295;
                    c[1] = c[1] + k & 4294967295;
                    c[2] =
                        c[2] + t & 4294967295;
                    c[3] = c[3] + ba & 4294967295;
                    c[4] = c[4] + A & 4294967295;
                    c[5] = c[5] + n & 4294967295;
                    c[6] = c[6] + p & 4294967295;
                    c[7] = c[7] + w & 4294967295
                }
                a.g = P(c[0]) + P(c[1]) + P(c[2]) + P(c[3]) + P(c[4]) + P(c[5]) + P(c[6]) + P(c[7])
            }
            return a.g
        };
        function R(a, b) {
            this.g = a;
            this.b = b || 0
        }

        R.prototype.read = function (a, b) {
            var c = a.read(this.g, b || this.b);
            b || (this.b += a.u);
            return c
        };
        function eb(a, b, c) {
            for (var d = a.b, e = [], f = 0; f < c; f += 1)e.push(b.read(a.g, d)), d += b.u;
            a.b += b.u * c;
            return e
        };
        var fb = {
            u: 1, read: function (a, b) {
                return a.getUint8(b || 0)
            }
        }, S = {
            u: 2, read: function (a, b) {
                return a.getUint16(b || 0)
            }
        }, T = {
            u: 4, read: function (a, b) {
                return a.getUint32(b || 0)
            }
        }, gb = {
            u: 4, read: function (a, b) {
                return a.getUint32(b || 0)
            }
        };

        function U(a) {
            return a % 4 ? a + (4 - a % 4) : a
        }

        function V(a, b) {
            var c = new Uint8Array(a.buffer, a.byteOffset, a.byteLength);
            (new Uint8Array(b.buffer, b.byteOffset, b.byteLength)).set(c, 0)
        }

        function W(a) {
            var b = 0, c;
            for (c in a)b += a[c].u;
            return {
                u: b, read: function (b, c) {
                    var d = c || 0, e = {}, g;
                    for (g in a)e[g] = a[g].read(b, d), d += a[g].u;
                    return e
                }
            }
        }

        function hb(a) {
            for (var b = new Uint32Array(4), c = 0; c < a.byteLength; c += 4)b[0] += a.getUint32(c);
            return b[0]
        };
        var X = W({type: T, I: S, C: S, i: S, A: S}), Y = W({tag: gb, b: T, offset: T, length: T});

        function ib(a) {
            this.arrayBuffer = a;
            this.A = new R(new DataView(a));
            this.m = [];
            this.i = [];
            this.g = [];
            this.b = {};
            a = this.A.read(X);
            if (1330926671 == a.type || 65536 == a.type) {
                a = eb(this.A, Y, a.I);
                for (var b = 0; b < a.length; b++) {
                    var c = a[b];
                    this.g.push(c.tag);
                    this.b[c.tag] = new DataView(this.arrayBuffer, c.offset, U(c.length));
                    this.m[b] = c.length;
                    this.i[b] = c.offset
                }
            } else throw Error("Font data is invalid");
        }

        function jb(a, b) {
            for (var c = [], d = X.u + Y.u * a.g.length, e = 0; e < a.g.length; e++) {
                var f = a.g[e], h = b.g[f] || null;
                if (h) {
                    for (var f = U(h.length) - U(a.m[e]), g = 0; g < a.g.length; g++)e !== g && a.i[g] > a.i[e] && (a.i[g] += f);
                    a.m[e] = h.length
                }
                d += U(a.m[e])
            }
            d = new ArrayBuffer(d);
            V(new DataView(a.arrayBuffer, 0, X.u), new DataView(d, 0, X.u));
            for (e = 0; e < a.g.length; e++) {
                f = a.g[e];
                if (h = b.g[f] || null)for (1668112752 !== f && 1195661646 !== f && V(a.b[f], new DataView(d, a.i[e], U(a.m[e]))), a.b[f] = new DataView(d, a.i[e], U(a.m[e])), h = h.F, g = 0; g < h.length; g++)h[g].apply(a.b[f]);
                else V(a.b[f], new DataView(d, a.i[e], U(a.m[e]))), a.b[f] = new DataView(d, a.i[e], U(a.m[e]));
                1751474532 === f && a.b[f].setUint32(8, 0);
                1330851634 === f && a.b[f].setUint16(8, 0);
                c[e] = hb(a.b[f])
            }
            g = new DataView(d, X.u, Y.u * a.g.length);
            for (e = 0; e < a.g.length; e++)f = a.g[e], g.setUint32(e * Y.u + 0, f), g.setUint32(e * Y.u + 4, c[e]), g.setUint32(e * Y.u + 8, a.i[e]), g.setUint32(e * Y.u + 12, a.m[e]);
            c = 2981146554 - hb(new DataView(d));
            a.b[1751474532].setUint32(8, c);
            a.arrayBuffer = d
        };
        function kb(a, b) {
            this.tag = a;
            this.length = b;
            this.F = []
        };
        function lb(a, b, c) {
            this.type = a;
            this.offset = b;
            this.b = c
        }

        var mb = W({offset: T, D: T, J: T});
        lb.prototype.apply = function (a) {
            if (1 === this.type || 2 === this.type) V(this.b, new DataView(a.buffer, a.byteOffset + this.offset, this.b.byteLength)); else if (3 === this.type) {
                var b = this.b.getUint32(0), c = new DataView(a.buffer, a.byteOffset + this.offset, a.byteLength - this.offset), d = new DataView(a.buffer, a.byteOffset + this.offset - b, a.byteLength - this.offset);
                V(c, d)
            } else if (4 === this.type)for (var c = new R(this.b), e = eb(c, mb, this.b.byteLength / mb.u), b = 0; b < e.length; b++)c = new DataView(a.buffer, a.byteOffset + e[b].offset, e[b].D),
                d = new DataView(a.buffer, a.byteOffset + e[b].offset + e[b].J, e[b].D), V(c, d); else if (5 === this.type)for (c = new R(this.b); c.b < this.b.byteLength;)for (d = c.read(S), e = c.read(S), b = 0; b < e; b++)for (var f = c.read(T), h = c.read(T); f < h;)a.setUint16(f, a.getUint16(f) + d), f += 2
        };
        function nb(a) {
            this.b = new R(new DataView(a));
            this.g = {};
            this.i = [];
            this.status = this.b.read(fb);
            if (0 === this.status) {
                this.b.b = 10;
                for (var b = eb(this.b, ob, this.b.read(S)), c = 0; c < b.length; c++) {
                    var d = new kb(b[c].tag, b[c].length);
                    this.i.push(d);
                    this.g[b[c].tag] = d
                }
                b = this.b.read(S);
                for (c = 0; c < b; c++)for (var e = this.b.read(pb), d = this.g[e.tag], f = 0; f < e.H; f++) {
                    var h = this.b.read(qb), g = new DataView(a, this.b.b, h.length);
                    d.F.push(new lb(h.type, h.offset, g));
                    this.b.b += h.length
                }
            }
        }

        var ob = W({tag: gb, g: T, offset: T, length: T}), pb = W({tag: gb, K: fb, m: T, H: S}), qb = W({
            type: fb,
            offset: T,
            length: T
        });
        var rb = !!window.ArrayBuffer;

        function sb(a, b, c) {
            var d = c || {};
            this.url = new ua(b);
            this.unicode = new m(ea(d.unicodeRange || ""));
            this.g = new sa(ra(d.featureSettings || ""));
            delete d.featureSettings;
            this.subset = new N(r(this.unicode), ta(this.g));
            this.source = tb(this, this.subset);
            this.w = new J(a, this.source, d);
            this.o = "unloaded";
            this.b = null;
            Object.defineProperties(this, {
                family: {
                    get: function () {
                        return this.w.family
                    }
                }, style: {
                    get: function () {
                        return this.w.style
                    }
                }, weight: {
                    get: function () {
                        return this.w.weight
                    }
                }, stretch: {
                    get: function () {
                        return this.w.stretch
                    }
                }, unicodeRange: {
                    get: function () {
                        return aa(r(this.unicode))
                    }
                },
                featureSettings: {
                    get: function () {
                        return this.w.featureSettings
                    }
                }, status: {
                    get: function () {
                        return this.o
                    }
                }, dynamic: {
                    get: function () {
                        return d.dynamic || !1
                    }
                }
            });
            this.i = Promise.resolve(this);
            this.m = []
        }

        function ub(a) {
            return {style: a.style, weight: a.weight, stretch: a.stretch, unicodeRange: a.unicodeRange}
        }

        function qa(a) {
            var b = a.weight;
            return a.style[0] + ("b" === b[0] ? "7" : "n" === b[0] ? "4" : b[0])
        }

        function tb(a, b) {
            return "url(" + E(a.url, b.get("l")) + ') format("woff2"),url(' + E(a.url, b.get("d")) + ') format("woff"),url(' + E(a.url, b.get("m")) + ') format("opentype")'
        }

        function vb(a, b) {
            var c = E(a.url, b.get("m"));
            if (4096 >= c.length)return Q(c).then(function (a) {
                if (200 === a.status)return a.body;
                throw a;
            });
            var d = null, d = b.b ? new Ya(b.unicode, null, a.b.b[1146703425]) : new Ya(b.unicode, b.g), c = ab(d), e = E(a.url, {
                format: "m",
                primer: c
            });
            return Q(e).then(function (a) {
                return 200 === a.status ? a.body : $a(d).then(function () {
                        return Q(e).then(function (a) {
                            if (200 === a.status)return a.body;
                            throw a;
                        })
                    })
            })
        }

        sb.prototype.load = function () {
            var a = this;
            a.A || (a.A = new Promise(function (b, c) {
                rb ? a.dynamic && "unloaded" === a.o ? (a.o = "loading", vb(a, a.subset).then(function (b) {
                            a.b = new ib(b);
                            b = a.b;
                            var c;
                            c = new Uint8Array(new ArrayBuffer(1));
                            c[0] = 1;
                            c = new nb(c.buffer);
                            jb(b, c);
                            a.w = new J(a.family, (new DataView(a.b.arrayBuffer)).buffer, ub(a));
                            return a.w.load()
                        }).then(function () {
                            a.o = "loaded";
                            b(a)
                        })["catch"](function () {
                            a.o = "error";
                            c(a)
                        })) : a.w.load().then(function () {
                            a.o = "loaded";
                            b(a)
                        })["catch"](function () {
                            a.o = "error";
                            c(a)
                        }) : b(a)
            }));
            return a.A
        };
        sb.prototype.update = function (a) {
            var b = this;
            b.m.push(a);
            b.i = b.i.then(function () {
                if (!b.dynamic)return Promise.reject(b);
                var a = new m(ea(b.m.join(",")));
                b.m = [];
                var d = fa(b.unicode, a);
                if (!d.b.length)return Promise.resolve(b);
                b.unicode = x(b.unicode, d);
                b.subset = new N(r(b.unicode), ta(b.g));
                return "unloaded" === b.o ? Promise.resolve(b) : b.load().then(function () {
                        if (rb) {
                            if (b.b instanceof ib) {
                                var a = b.b, c = new N(r(d), null, a.b[1195661646]);
                                return vb(b, c).then(function (d) {
                                    d = new nb(d);
                                    if (0 === d.status)return jb(a, d), b.subset =
                                        c, b.b = a, b.w = new J(b.family, (new DataView(b.b.arrayBuffer)).buffer, ub(b)), b.w.load().then(function () {
                                        K.add(b.w);
                                        return b
                                    });
                                    b.subset = c;
                                    return b
                                })
                            }
                            throw b;
                        }
                        var h = x(b.unicode, d), c = new N(r(h), ta(b.g));
                        b.unicode = h;
                        b.subset = c;
                        b.source = tb(b, c);
                        b.w = new J(b.family, b.source, ub(b));
                        K.add(b.w);
                        return b
                    })
            });
            return b.i
        };
        function Z() {
            this.fonts = [];
            Object.defineProperties(this, {
                status: {
                    get: function () {
                        for (var a = 0; a < this.fonts.length; a++)if ("loading" === this.fonts[a].status)return "loading";
                        return "loaded"
                    }
                }, size: {
                    get: function () {
                        return this.fonts.length
                    }
                }
            })
        }

        Z.prototype.has = function (a) {
            return -1 !== this.fonts.indexOf(a)
        };
        Z.prototype.add = function (a) {
            this.has(a) || (K.add(a.w), this.fonts.push(a));
            return this
        };
        Z.prototype["delete"] = function (a) {
            var b = this.fonts.indexOf(a);
            -1 !== b && this.fonts.splice(b, 1);
            return K["delete"](a.w)
        };
        Z.prototype.forEach = function (a) {
            var b = this;
            this.fonts.forEach(function (c, d) {
                a(c, d, b)
            })
        };
        function wb(a) {
            this.url = new ua(a.ping);
            this.m = a.p;
            this.i = a.h;
            this.g = a.a;
            this.C = a.t;
            this.version = a.j;
            this.A = window.location.hostname;
            this.app = a.l || "";
            this.b = [];
            var b = this;
            setInterval(function () {
                b.b.length && (Q(E(b.url, {
                    s: b.m,
                    k: b.C,
                    ht: b.i,
                    h: b.A,
                    f: b.b.join("."),
                    a: b.g,
                    js: b.version,
                    app: b.app,
                    _: Date.now()
                })), b.b = [])
            }, 300)
        }

        function xb(a, b) {
            b.length && (a.b = a.b.concat(b))
        };
        function yb(a) {
            this.g = new m;
            this.b = new m(ea("U+20-7E"));
            a && (this.b = x(this.b, a))
        }

        yb.prototype.set = function (a) {
            this.g = x(this.g, a)
        };
        yb.prototype.get = function () {
            return x(this.b, this.g)
        };
        function zb(a) {
            this.B = [];
            this.fonts = new Z;
            this.cache = new yb;
            this.ping = new wb(a);
            this.b = a.c;
            a.f && (a.f.forEach(function (a) {
                this.B.push(new sb(a.family, a.source, a.descriptors))
            }, this), a.ping && xb(this.ping, a.f.map(function (a) {
                return a.id
            })))
        }

        function Ab(a) {
            a.B.forEach(function (b) {
                b.dynamic && b.update(aa(r(a.cache.get())))
            })
        }

        function Bb(a) {
            if (a.b && a.b.length) {
                for (var b = document.createElement("style"), c = "", d = 0; d < a.b.length; d += 2)c += a.b[d] + "{font-family:" + a.b[d + 1] + ";}";
                b.textContent = c;
                document.head.appendChild(b)
            }
        }

        zb.prototype.load = function (a) {
            var b = this, c = new ma(a);
            c.loading();
            wa(function () {
                b.cache.set(new m(ha(document.body)));
                ka((new la(function (a) {
                    b.cache.set(new m(a));
                    Ab(b)
                })).b);
                Promise.all(b.B.map(function (a) {
                    pa(c, a);
                    return a.dynamic ? a.update(aa(r(b.cache.get()))).then(function () {
                            return a.load()
                        })["catch"](function (b) {
                            oa(c, a);
                            throw b;
                        }) : a.load()["catch"](function (b) {
                            oa(c, a);
                            throw b;
                        })
                })).then(function () {
                    b.B.map(function (a) {
                        z(c.b, D(a, "loading"));
                        B(c.b, D(a, "active"));
                        C(c, "fontactive", a);
                        b.fonts.add(a)
                    });
                    na(c)
                })["catch"](function () {
                    c.inactive()
                })
            });
            Bb(b)
        };
        var Cb = new zb(config);
        window.Typekit = {};
        window.Typekit.load = Cb.load.bind(Cb);
        window.Typekit.fonts = Cb.fonts;
        window.Typekit.kit = Cb.B;
        window.Typekit.Font = function (a, b, c) {
            var d = window.Typekit.user, e = window.Typekit.token, f = c || {}, h = (f.style || "normal").toString(), f = (f.weight || "normal").toString();
            /^(normal|italic|oblique)$/.test(h) || (h = "normal");
            /^(([1-9]00)|normal|bold)$/.test(f) || (f = "400");
            h = h[0] + ("b" === f[0] ? "7" : "n" === f[0] ? "4" : f[0]);
            b = config.preview.replace("{user}", encodeURIComponent(d)).replace("{font_alias}", encodeURIComponent(b)).replace("{fvd}", encodeURIComponent(h)) + "&token=" + encodeURIComponent(e);
            return new sb(a, b, c)
        };
    }());
}({
    "a": "7911652",
    "h": "tk",
    "t": "cwj3gfk",
    "p": 1,
    "j": "1.1.13",
    "c": [".tk-source-han-serif-sc", "\"source-han-serif-sc\",serif"],
    "l": "typekit",
    "type": "dynamic",
    "preview": "https://use.typekit.net/pf/{user}/{font_alias}/{fvd}/{format}{?subset_id,primer,token,unicode,features,gdyn,v}",
    "ping": "https://p.typekit.net/p.gif{?s,k,ht,h,f,a,js,app,_}",
    "dl": "AAAAHQAAAAoE4I98",
    "f": [{
        "source": "https://use.typekit.net/af/a38f52/00000000000000003b9af8dc/27/{format}{?primer,unicode,gdyn,features,v}",
        "id": 33866,
        "dynamic": true,
        "family": "source-han-serif-sc",
        "descriptors": {
            "unicodeRange": "U+20-7E,U+A0-FF,U+152-153,U+2013-2014,U+2018-2019,U+201C-201E,U+2022,U+2026,U+2039-203A,U+20AC,U+2122",
            "featureSettings": "\"ALL \"",
            "subset": "",
            "dynamic": true
        }
    }, {
        "source": "https://use.typekit.net/af/de93fe/00000000000000003b9af8e2/27/{format}{?primer,unicode,gdyn,features,v}",
        "id": 33868,
        "dynamic": true,
        "family": "source-han-serif-sc",
        "descriptors": {
            "weight": 900,
            "unicodeRange": "U+20-7E,U+A0-FF,U+152-153,U+2013-2014,U+2018-2019,U+201C-201E,U+2022,U+2026,U+2039-203A,U+20AC,U+2122",
            "featureSettings": "\"ALL \"",
            "subset": "",
            "dynamic": true
        }
    }]
}))