import React, {Component} from "react";
import Client from "./Client/Client";
import User from "./User/User"
import "./App.css"

class App extends Component {

    // Making timer
    constructor(props) {
        super(props);
        this.state = {date: new Date(), page: "client"}; //page: client or user
    }

    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        );
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    tick() {
        this.setState({
            date: new Date()
        });
    }

    // Always displayed
    headingContent() {
        return (
            <div id="heading">
                <div id="logo">
                    <i>Nasze Zoo</i>
                </div>
                <div id="clock">
                    {this.state.date.toLocaleTimeString()}
                </div>
                <div className={`clientButton ${this.state.page === "client" ? 'clicked' : ''}`}
                     onClick={() => this.setState({page: "client"})}>
                    Dla odwiedzających
                </div>
                <div className={`workerButton ${this.state.page === "worker" ? 'clicked' : ''}`}
                     onClick={() => this.setState({page: "worker"})}>
                    Dla pracowników
                </div>
                <div style={{clear: "both"}}/>
            </div>
        )
    }

    mainContent() {
        if (this.state.page === "client") {
            return <Client/>
        } else {
            return <User date={this.state.date}/>
        }
    }

    render() {
        return (
            <div>
                {this.headingContent()}
                {this.mainContent()}
            </div>
        );
    }
}

export default App;