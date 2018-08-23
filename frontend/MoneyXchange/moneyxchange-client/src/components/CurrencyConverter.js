import React, { Component } from 'react';
import { Button } from 'mdbreact';
import CurrencyInput from 'react-currency-input';

import CurrencyRateApi from '../services/CurrencyRateApi';

class CurrencyConverter extends Component {

  constructor(props) {
    super(props);
    this.state = {
      latest: {
        base: undefined
      },
      toCurrency: {
        code: undefined
      },
      fromCValue: '0',
      fromCMask: '',
      toCValue: '0',
      loggedIn: false
    };

    this.currencyRateApi = new CurrencyRateApi();
  }

  componentDidMount() {
    this.updateRates();
  }

  updateRates = () => {
    try {
      this.currencyRateApi.getLatest().then(function (json) {
        if(!json){
          return; //TODO controlar error
        }
        this.setState({
          latest: json,
          toCurrency: json.rates[0]
        });

        this.calculate();
      }.bind(this));
    } catch (e) {
      //TODO Desplegar error al usuario
    }

    console.log("Rates actualizados");
    setTimeout(this.updateRates, 10 * 1000); //Every 10 seconds
  }

  handleChange = (e, maskedvalue, floatvalue) => {
    this.setState({
      fromCValue: floatvalue,
      fromCMask: maskedvalue
    });
  }

  calculate = () => {
    let result = this.state.fromCValue * this.state.toCurrency.rate;

    this.setState({
      toCValue: result
    });
  }

  render() {
    return (
      <div>
        <div className="row">
          <div className="col">
            {this.state.latest.base}
          </div>
          <div className="col">
            {this.state.toCurrency.code + ' - ' + this.state.toCurrency.rate}
          </div>
        </div>
        <div className="row">
          <div className="col">
            <div className="input-group">
              <span className="input-group-addon">$</span>
              <CurrencyInput value={this.state.fromCValue} onChangeEvent={this.handleChange}
                className="form-control" decimalSeparator="." thousandSeparator="," precision="4" />
            </div>
          </div>
          <div className="col">
            <div className="input-group">
              <span className="input-group-addon">€</span>
              <CurrencyInput disabled value={this.state.toCValue} className="form-control"
                decimalSeparator="." thousandSeparator="," precision="4" />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col"><Button size="lg" onClick={this.calculate}>CALCULATE</Button></div>
        </div>
      </div>
    );
  }


}

export default CurrencyConverter;