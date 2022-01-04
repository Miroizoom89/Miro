import React, {Component} from "react";
import Tablebox from "./Table";
import Form from "./Form";

class Admin extends Component {

    /* STATES
    * category: name of currently chosen category e.g species/animals
    * list: list of objects of currently chosen category
    * object: one, currently chosen object from list
    * action: currently chosen action e.g add/delete
    * message: message after confirming form in dialog window
    * logins: list of all logins - used in adding/editing task
    * cages: list of all cages - used in adding/editing task
    * species: list of all species - used in adding/editing animal
     */
    state = {
        category: "none",
        list: [],
        object: null,
        action: "",
        message: "",
        logins: [],
        cages: [],
        species: [],

    }

    // This method is activated every time you choose a category. It downloads list of object of currently chosen category
    async ctgrChosen(ctgrEN) {
        const res = await fetch("http://localhost:8080/api/v1/" + ctgrEN + "/list")
            .then(response => response.json())
            .then(list => {
                console.log(list)
                    if (ctgrEN !== this.state.category) {
                        this.setState({category: ctgrEN, list: list, action: "", message: ""})
                    } else {
                        this.setState({category: ctgrEN, list: list, action: ""})
                    }
                }
            );
    }

    // This method is activated every time you choose an action. It may download some list e.g species if needed
    async actionChosen(object, action) {
        let logins = []
        let cages = []
        let species = []
        if (this.state.category === "tasks" && action !== "delete") {
            await fetch("http://localhost:8080/api/v1/zookeepers/listOfLogins")
                .then(response => response.json())
                .then(res => {
                    logins = res
                })
            await fetch("http://localhost:8080/api/v1/species/speciesCageList")
                .then(response => response.json())
                .then(res => {
                    cages = res
                })
        } else if (this.state.category === "animals" && action !== "delete") {
            await fetch("http://localhost:8080/api/v1/species/speciesList")
                .then(response => response.json())
                .then(res => {
                    species = res
                })
        }
        this.setState({object: object, action: action, message: "", logins: logins, cages: cages, species: species})
    }

    // Updates message given from the server or DialWin.js
    newMsg(newMsg) {
        this.setState({message: newMsg})
    }

    render() {
        const ctgrsList = [["Zwierzęta", "animals"], ["Gatunki", "species"], ["Zadania", "tasks"], ["Opiekuni", "zookeepers"], ["Użytkownicy", "users"]]
        return (
            <div>
                <div id="logInfo">
                    Zalogowano jako: admin {this.props.login}
                </div>
                <div id="categories">
                    {ctgrsList.map(title => <div
                        className={`categories ${this.state.category === title[1] ? 'clicked' : ''}`}
                        onClick={() => this.ctgrChosen(title[1])}>
                        {title[0]}
                    </div>)}
                    <div style={{clear: "both"}}/>
                </div>
                <Tablebox
                    category={this.state.category}
                    list={this.state.list}
                    sendToAdmin={this.actionChosen.bind(this)}
                />
                <Form
                    category={this.state.category}
                    object={this.state.object}
                    action={this.state.action}
                    updateList={this.ctgrChosen.bind(this)}
                    msg={this.state.message}
                    newMsg={this.newMsg.bind(this)}
                    logins={this.state.logins}
                    cages={this.state.cages}
                    species={this.state.species}
                />
                <div style={{clear: "both"}}/>
            </div>
        );
    }
}

export default Admin