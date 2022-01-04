import React, {Component} from "react";

class Form extends Component {

    // Displays form depending on chosen category and action
    dialWin() {
        if (this.props.action !== '') {
            if (this.props.action === 'delete') {
                return (
                    <div id="dialWinCont">
                        Czy na pewno chcesz usunąć dany obiekt?
                    </div>
                )
            } else if (this.props.category === 'species') {
                return this.showSpecies()
            } else if (this.props.category === 'animals') {
                return this.showAnimals()
            } else if (this.props.category === 'users') {
                return this.showUsers()
            } else if (this.props.category === 'tasks') {
                return this.showTasks()
            } else if (this.props.category === 'zookeepers') {
                return this.showZookeepers()
            }
        }
    }

    // Following show-methods are responsible for displaying form
    showSpecies() {
        let name = ''
        let cage = ''
        let description = ''
        let feeding = ''
        let speciesName
        if (this.props.action === 'update') {
            name = this.props.object.species
            cage = this.props.object.cage
            description = this.props.object.description
            feeding = this.props.object.feedingHour.substring(0, 5)
        } else if (this.props.action === 'add') {
            name = ''
            cage = ''
            description = ''
            feeding = ''
        }
        if (this.props.action === 'add') {
            speciesName = <div> Nazwa gatunku: <br/>
                <input type="text" id="name" autoFocus={true} placeholder="np. lew" required={true}
                       defaultValue={name}/> <br/></div>
        } else {
            speciesName = <div id="name"></div>
        }
        return (
            <div id="dialWinCont">
                {speciesName}
                Wybieg: <br/>
                <input type="text" id="cage" placeholder="np. A13" required={true} defaultValue={cage}/> <br/>
                Opis: <br/>
                <textarea id="description" cols="40" rows="5" placeholder="Opisz gatunek" defaultValue={description}/>
                <br/>
                Pora karmienia: <br/>
                <input type="text" id="feeding" placeholder="np. 18:00" required={true} defaultValue={feeding}/> <br/>
            </div>
        )
    }

    showAnimals() {
        let name = ''
        let species = ''
        let dob = ''
        if (this.props.action === 'update') {
            name = this.props.object.name
            species = this.props.object.species
            dob = this.props.object.dob
        } else if (this.props.action === 'add') {
            name = ''
            species = ''
            dob = ''
        }
        return (
            <div id="dialWinCont">
                Imię zwierzaka: <br/>
                <input type="text" id="name" autoFocus={true} placeholder="np. Funiek" required={true}
                       defaultValue={name}/> <br/>
                Gatunek: <br/>
                <select id="species">ww
                    {this.props.species.map(specie => {
                        if (specie === species) {
                            return <option value={specie} selected>{specie}</option>
                        } else {
                            return <option value={specie}>{specie}</option>
                        }
                    })}
                </select>
                {/*<input type="text" id="species" placeholder="np. lew" required={true} defaultValue={species}/> */}
                <br/>
                Data urodzenia: <br/>
                <input type="text" id="dob" placeholder="np. 2000-12-09" required={true} defaultValue={dob}/> <br/>
            </div>
        )
    }

    showUsers() {
        let login = ''
        let pass = ''
        let role = ''
        if (this.props.action === 'update') {
            login = this.props.object.login
            pass = ''
            role = this.props.object.role.toUpperCase()
        } else if (this.props.action === 'add') {
            login = ''
            pass = ''
            role = ''
        }
        return (
            <div id="dialWinCont">
                Login: <br/>
                <input type="text" id="login" autoFocus={true} required={true} defaultValue={login}/> <br/>
                Hasło: <br/>
                <input type="text" id="pass" placeholder="conajmniej 8 znaków" required={true} defaultValue={pass}/>
                <br/>
                Stanowisko: <br/>
                <input type="text" id="role" placeholder="admin/user" required={true} defaultValue={role}/> <br/>
            </div>
        )
    }

    showTasks() {
        let login = ''
        let description = ''
        let cage = ''
        let startTime = ''
        let duration = ''
        if (this.props.action === 'update') {
            login = this.props.object.login
            description = this.props.object.description
            cage = this.props.object.cage
            startTime = this.props.object.startTime.substring(0, 10) + " " + this.props.object.startTime.substring(11, 16)
            duration = this.props.object.duration.substring(0, 5)
        } else if (this.props.action === 'add') {
            description = ''
            cage = ''
            startTime = ''
            duration = ''
        }
        return (
            <div id="dialWinCont">
                Login opiekuna: <br/>
                <select id="login">
                    {this.props.logins.map(logins => {
                        if (logins === login) {
                            return <option value={logins} selected>{logins}</option>
                        } else {
                            return <option value={logins}>{logins}</option>
                        }
                    })}
                </select>
                {/*<input type="text" id="login" autoFocus= {true} required={true} defaultValue={login}/>*/}
                <br/>
                Treść: <br/>
                <textarea id="description" cols="40" rows="5" placeholder="Opisz zadanie" defaultValue={description}/>
                <br/>
                Wybieg: <br/>
                <select id="cage">
                    {this.props.cages.map(cages => {
                        if (cages === cage) {
                            return <option value={cages} selected>{cages}</option>
                        } else {
                            return <option value={cages}>{cages}</option>
                        }
                    })}
                </select>
                {/*<input type="text" id="cage" placeholder="np. A13" required={true} defaultValue={cage}/> <br/>*/}
                <br/>
                Początek zadania: <br/>
                <input type="text" id="startTime" placeholder="YYYY:MM:DD hh:mm" required={true}
                       defaultValue={startTime}/> <br/>
                Oczekiwany czas trwania zadania: <br/>
                <input type="text" id="duration" placeholder="hh:mm" required={true} defaultValue={duration}/> <br/>
            </div>
        )
    }

    showZookeepers() {
        let name = ''
        let surname = ''
        let login = ''
        if (this.props.action === 'update') {
            name = this.props.object["first"].name
            surname = this.props.object["first"].surname
            login = this.props.object["first"].login
        } else if (this.props.action === 'add') {
            name = ''
            surname = ''
            login = ''
        }
        return (
            <div id="dialWinCont">
                Imię: <br/>
                <input type="text" id="name" autoFocus={true} required={true} defaultValue={name}/> <br/>
                Nazwisko: <br/>
                <input type="text" id="surname" required={true} defaultValue={surname}/> <br/>
                Login: <br/>
                <input type="text" id="login" required={true} defaultValue={login}/> <br/>
            </div>
        )
    }

    // This method validates form and forces user to write date/time form fields specifically
    collectInput() {
        if (this.props.category === 'species') {
            const name = document.getElementById("name").value
            const cage = document.getElementById("cage").value
            const description = document.getElementById("description").value
            const feeding = document.getElementById("feeding").value + ":00"
            if (parseInt(feeding.substring(0, 2)) > 24 || parseInt(feeding.substring(3, 5)) > 60) {
                throw "Podana godzina jest niepoprawna"
            }
            if (feeding.substring(2, 3) !== ':' || feeding.length !== 8) {
                throw "Użyty format godziny jest niepoprawny. Użyj: hh:mm"
            }
            if (this.props.action === 'add') {
                return (
                    {
                        species: name,
                        cage: cage,
                        description: description,
                        feedingHour: feeding
                    }
                )
            } else {
                console.log(this.props.object.species)
                return (
                    {
                        id: this.props.object.id,
                        cage: cage,
                        description: description,
                        feedingHour: feeding,
                        species: this.props.object.species
                    }
                )
            }
        } else if (this.props.category === 'animals') {
            const now = Date.now()
            const name = document.getElementById("name").value
            const species = document.getElementById("species").value
            const dob = document.getElementById("dob").value
            if (isNaN(Date.parse(dob))) {
                throw "Podana data jest niepoprawna"
            }
            if (Date.parse(dob) > now) {
                throw "System przyjmuje tylko te urodziny, które się już odbyły, a nie które się dopiero odbędą "
            }
            if (dob.substring(4, 5) !== '-' || dob.substring(7, 8) !== '-') {
                throw "Użyty format daty jest niepoprawny. Użyj: YYYY-MM-DD"
            }
            if (this.props.action === 'add') {
                return (
                    {
                        name: name,
                        species: species,
                        dob: dob,
                    }
                )
            } else {
                return (
                    {
                        id: this.props.object.id,
                        name: name,
                        species: species,
                        dob: dob,
                    }
                )
            }
        } else if (this.props.category === 'users') {
            const login = document.getElementById("login").value
            const pass = document.getElementById("pass").value
            const role = document.getElementById("role").value
            if (pass.length < 8) {
                throw "Podane hasło jest za krótkie"
            }
            if (role.toLowerCase() !== "admin" && role.toLowerCase() !== "user") {
                throw "Podana rola nie istnieje"
            }
            if (this.props.action === 'add') {
                return (
                    {
                        login: login,
                        pass: pass,
                        role: role,
                    }
                )
            } else {
                return (
                    {
                        id: this.props.object.id,
                        login: login,
                        pass: pass,
                        role: role,
                    }
                )
            }
        } else if (this.props.category === 'tasks') {
            const now = Date.now()
            const login = document.getElementById("login").value
            const description = document.getElementById("description").value
            const cage = document.getElementById("cage").value
            const duration = document.getElementById("duration").value + ":00"
            const startTime_origin = document.getElementById("startTime").value
            const startTime = startTime_origin.substring(0, 10) + "T" + startTime_origin.substring(11, 16) + ":00"
            if (isNaN(Date.parse(startTime))) {
                throw "Podana data nie istnieje"
            }
            if (Date.parse(startTime) < now) {
                throw "Prawo nie działa wstecz"
            }
            if (startTime.substring(4, 5) !== '-' || startTime.substring(7, 8) !== '-') {
                throw "Użyty format daty jest niepoprawny. Użyj: YYYY-MM-DD"
            }
            if (parseInt(startTime.substring(11, 13)) > 24 || parseInt(startTime.substring(14, 16)) > 60) {
                throw "Podana godzina jest niepoprawna"
            }
            if (startTime.substring(13, 14) !== ':' || startTime.length !== 19) {
                throw "Użyty format godziny jest niepoprawny. Użyj: hh:mm"
            }
            if (parseInt(duration.substring(0, 2)) > 24 || parseInt(duration.substring(3, 5)) > 60) {
                throw "Podana godzina jest niepoprawna"
            }
            if (duration.substring(2, 3) !== ':' || duration.length !== 8) {
                throw "Użyty format godziny jest niepoprawny. Użyj: hh:mm"
            }
            if (this.props.action === 'add') {
                return (
                    {
                        cage: cage,
                        description: description,
                        duration: duration,
                        login: login,
                        startTime: startTime
                    }
                )
            } else {
                return (
                    {
                        id: this.props.object.id,
                        cage: cage,
                        description: description,
                        duration: duration,
                        login: login,
                        startTime: startTime,
                        species: this.props.object.species,
                        zooKeeper: this.props.object.zooKeeper,
                    }
                )
            }
        } else if (this.props.category === 'zookeepers') {
            const name = document.getElementById("name").value
            const surname = document.getElementById("surname").value
            const login = document.getElementById("login").value
            if (this.props.action === 'add') {
                return (
                    {
                        name: name,
                        surname: surname,
                        login: login
                    }
                )
            } else {
                return (
                    {
                        id: this.props.object.id,
                        name: name,
                        surname: surname,
                        login: login

                    }
                )
            }
        }
    }

    // This method is called after clicking confirmation button. It sends given form to server with suitable method e.g DELETE
    async confirm() {
        if (this.props.action === 'delete') {
            try {
                const res = await fetch("http://localhost:8080/api/v1/" + this.props.category + "/delete/" + this.props.object.id,
                    {method: "DELETE"})
                const text = await res.text();
                const json = JSON.parse(text)
                if (!json['first']) throw json['second']
                this.props.updateList(this.props.category)
                this.props.newMsg("Operacja się powiodła")
            } catch (err) {
                this.props.newMsg("Operacja się nie powiodła: " + err)
            }
        } else if (this.props.action === 'add') {
            try {
                const data = this.collectInput()
                const res = await fetch("http://localhost:8080/api/v1/" + this.props.category + "/add",
                    {
                        method: "POST",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(data)
                    })
                const text = await res.text();
                const json = JSON.parse(text)
                if (!json['first']) throw json['second']
                this.props.updateList(this.props.category)
                this.props.newMsg("Operacja się powiodła")
            } catch (err) {
                this.props.newMsg("Operacja się nie powiodła: " + err)
            }
        } else if (this.props.action === 'update') {
            try {
                const data = this.collectInput()
                const res = await fetch("http://localhost:8080/api/v1/" + this.props.category + "/update",
                    {
                        method: "PUT",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(data)
                    })
                const text = await res.text();
                const json = JSON.parse(text)
                if (!json['first']) throw json['second']
                this.props.updateList(this.props.category)
                this.props.newMsg("Operacja się powiodła")
            } catch (err) {
                this.props.newMsg("Operacja się nie powiodła: " + err)
            }
        }
    }

    render() {
        return (
            <div id="DialWin">
                <div id="dialWinID" style={{fontSize: 20, fontWeight: "bold"}}>
                    {this.props.action === 'add' ? "Dodawanie obiektu:" : null}
                    {this.props.action === 'update' ? this.props.category === 'zookeepers' ? "Edytowane ID: " +this.props.object["first"].id : "Edytowane ID: " + this.props.object.id : null}
                    {this.props.action === 'delete' ? "Usuwane ID: " + this.props.object.id : null}
                </div>
                <br/>
                {this.dialWin()}
                {this.props.action !== '' ? <input type="submit" value="Potwierdź" onClick={() => this.confirm()}
                                                   style={{
                                                       marginTop: "20px",
                                                       width: "300px",
                                                       height: "50px",
                                                       fontSize: "40px"
                                                   }}/> : ''}
                {this.props.msg}
            </div>
        );
    }
}

export default Form