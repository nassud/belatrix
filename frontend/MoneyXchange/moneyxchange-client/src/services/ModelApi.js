import { Component } from 'react';

class ModelApi extends Component {

    constructor(props) {
        super(props);

        this.HOST = "http://localhost";
        this.PORT = "8080";
        this.API_VERSION = 'v1';

        this.username = 'user';
        this.password = 'pass';
    }

    getPath() {
        return this.HOST + ':' + this.PORT + '/' + this.API_VERSION
    }

    getAuthenticationHeaders() {
        var base64 = require('base-64');

        let headers = new Headers();
        headers.append('Authorization', 'Basic ' + base64.encode(this.username + ":" + this.password));
        return headers;
    }

}

export default ModelApi;