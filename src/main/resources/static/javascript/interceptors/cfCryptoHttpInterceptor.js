angular.module('myApp', 'angularjs-crypto').run('cfCryptoHttpInterceptor', function(cfCryptoHttpInterceptor) {

    cfCryptoHttpInterceptor.base64Key = "16rdKQfqN3L4TY7YktgxBw==";
    cfCryptoHttpInterceptor.pattern = "_enc";

    cfCryptoHttpInterceptor.plugin = {
        encode: function(plainValue, base64Key) {
            return plainValue;
        },
        decode: function(encryptedValue, base64Key) {
            return encryptedValue;
        }
    };

    cfCryptoHttpInterceptor.plugin = new CryptoJSCipher(CryptoJS.mode.ECB, CryptoJS.pad.Pkcs7, CryptoJS.DES);
    cfCryptoHttpInterceptor.logging = true;

});