/*
 * util.js
 * Creado el 28/Jul/2017 2:01:57 PM
 *
 */

String.prototype.format = function() {
	var args = arguments;

	return this.replace(/\{(\d+)\}/g, function() {
		return args[arguments[1]];
	});
};

var SIT = {

    /**
     * Permite obtener todos los parametros de la URL.
     * @returns {Array|SIT.getUrlParams.vars} una arreglo con los paramtres de la URL.
     */
    getUrlParams : function() {
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        var vars = [];

        for (var i = 0; i < hashes.length; i++) {
            var hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }

        return vars;
    },

    /**
     * Permite obtener un parametro de la URL.
     *
     * @param {type} name el nombre del parametro a obtener.
     * @returns {String} una cadena con el valor.
     */
    getUrlParam : function (name) {
        return SIT.getUrlParams()[name];
    },

    /**
     * Permite dar formato de moneda a un número.
     *
     * @param {Number} numero el número que será formateado.
     * @returns {String} el número formateado.
     */
    formatoMoneda : function (numero) {
        var neg = numero < 0;

        if (neg) {
            numero = Math.abs(numero);
        }

        return (neg ? "-$" : '$') + parseFloat(numero, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").toString();
    },

    /**
     * Permite dar formato de porcentaje a un número.
     *
     * @param {Number} parte el número que representa una parte del total.
     * @param {Number} total el número que representa el total.
     * @returns {String} el número en formato de porcentaje.
     */
    formatoPorcentaje : function (parte, total) {
        var porcentaje = SIT.porcentaje(parte, total);
        var formato = '{0}%';

        return formato.format(porcentaje);
    },

    /**
     * Permite calcular el porcentaje.
     *
     * @param {Number} parte el número que representa una parte del total.
     * @param {Number} total el número que representa el total.
     * @returns {Number} el porcentaje.
     */
    porcentaje : function (parte, total) {
        var porcentaje = (parte / total) * 100;
        return porcentaje.toFixed(2);
    },

    /**
     * Permite guardar una cookie en el cliente.
     *
     * @param {String} name el nombre de la cookie a guardar.
     * @param {String} value el valor que tendrá la cookie.
     * @param {Number} days el tiempo de vida de la cookie en días.
     */
    setCookie: function (name, value, days) {
        var expires;

        if (days !== -1) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        } else {
            expires = "";
        }

        document.cookie = encodeURIComponent(name) + "="
                + encodeURIComponent(value) + expires + "; path=/";
    },

    /**
     * Permite obtener el valor que contiene una cookie en el cliente.
     * @param {String} name el nombre de la cookie a buscar.
     * @returns {String} el valor que contiene la cookie.
     */
    getCookie: function (name) {
        var nameEQ = encodeURIComponent(name) + "=";
        var ca = document.cookie.split(';');

        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];

            while (c.charAt(0) === ' ')
                c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0)
                return decodeURIComponent(c.substring(nameEQ.length, c.length));
        }

        return null;
    },

    /**
     * Perimite eliminar una cookie en el cliente.
     * @param {String} name el nombre de la cookie que será eliminada.
     */
    removeCookie: function (name) {
        setCookie(name, "", -1);
    }
};

window.SIT = SIT;
