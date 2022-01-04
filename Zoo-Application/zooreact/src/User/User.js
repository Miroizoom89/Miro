import React, {Component} from "react";
import Logging from "./Logging";
import Admin from "./Admin"
import Zookeeper from "./Zookeeper"
import "./User.css"

class User extends Component {

    // role: office of currently logged user
    state = {
        login: "",
        role: null
    }

    // This method is used after successful logging in Logging.js
    loggedAs(login, role) {
        this.setState({login: login})
        this.setState({role: role})
    }

    // There are 3 options of main content: logging window, admin page and zookeeper page
    mainDisplay() {
        if (this.state.role === null) {
            return <Logging loggedAs={this.loggedAs.bind(this)}/>
        } else if (this.state.role === "ADMIN") {
            return <Admin login={this.state.login}/>
        } else if (this.state.role === "USER") {
            return <Zookeeper login={this.state.login} date={this.props.date}/>
        }
    }

    render() {
        return (
            <div className="Torso">
                {this.mainDisplay()}
            </div>
        );
    }
}

export default User