import ModelApi from './ModelApi';

class CurrencyRateApi extends ModelApi {

  getLatest = async (opts) => {
    const response = await fetch('http://localhost:8080/v1/currencyRate/latest?base=USD&symbols=EUR', {
      method: 'get',
      headers: this.getAuthenticationHeaders(),
      body: JSON.stringify(opts)
    });

    return response ? response.json() : false;
  }

}

export default CurrencyRateApi;