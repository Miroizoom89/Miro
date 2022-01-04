import React, {Component} from "react";
import '../App.css';

class SpeciesList extends Component {

  render() {
    return (
        <div className={`species ${this.props.disID === this.props.id ? 'clicked' : ''}`}
             onClick={() => this.props.justClicked(this.props.id)}>
          <li>{this.props.name}</li>
        </div>
    );
  }
}

export default SpeciesList;
