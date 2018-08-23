import React, { Component } from 'react';
import logo from './logo.svg';

//Custom components
import CurrencyConverter from './components/CurrencyConverter';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="header">
          <img src={logo} className="logo" alt="logo" />
          <h1 className="title">MoneyXchange</h1>
        </header>
        
        <main role="main" className="container margin-top">
          <CurrencyConverter />
        </main>

        <footer className="footer">
          <div className="container">
            <span className="text-muted">Footer content</span>
          </div>
        </footer>
      </div>
    );
  }
}

export default App;
