import React, {Component} from "react";

class Logging extends Component {

    // tried: activates after failed login attempt
    state = {
        tried: false
    }

    // Sends written login and password to the server and waits for answer if they are correct
    logIN() {
        const login = document.getElementById("login").value
        const pass = document.getElementById("pass").value
        fetch("http://localhost:8080/api/v1/users/userIsValid/" + login + "/" + pass)
            .then(response => response.json())
            .then(status => {
                    console.log(status);
                    if (status['first'] === true) {
                        this.props.loggedAs(login, status['second'])
                    } else {
                        this.setState({tried: true})
                    }
                }
            );
    }

    // Shows that previous login attempt failed
    ifTried() {
        if (this.state.tried === true) {
            return (
                "Niepoprawny login lub hasło!"
            )
        }
    }

    render() {
        return (
            <div id="logger">
                Login: <br/>
                <input id="login" type="text"/> <br/>
                Hasło: <br/>
                <input id="pass" type="password"/> <br/>
                <input type="submit" value="Zaloguj się" onClick={() => this.logIN()}/>
                {this.ifTried()}
            </div>
        );
    }
}

export default Logging