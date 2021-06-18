import React, { Component } from 'react';
import Home from './component/Home'

class App extends Component{
  state ={
    isOpen: false
  }
  render() {
  return (
    <div className="App">
      <Home></Home>
    </div>
  );
  }
}

export default App;
