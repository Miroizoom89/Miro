import React, {Component} from "react";
import TasksList from "./TasksList"
import TaskInfo from "./TaskInfo"

class Zookeeper extends Component {

    /* STATES
    * tasks: list of tasks assigned to logged zookeeper
    * task_chosen: task that was clicked and it's info is displaying on screen
    * id_chosen: id of preceding state that informs whether any task is chosen or not
    */
    state = {
        tasks: [],
        task_chosen: null,
        id_chosen: 0
    }

    // Downloads from the server list of tasks assigned to logged zookeeper
    componentDidMount() {
        fetch("http://localhost:8080/api/v1/tasks/taskByLogin/" + this.props.login)
            .then(response => response.json())
            .then(tasks => {
                    this.setState({tasks: this.sortResults(tasks), id_chosen: 0})
                }
            );
    }

    // This method sorts given list of tasks by remaining time
    sortResults(list) {
        function hhmmssToMs(hhmmss) {
            const a = hhmmss.split(':')
            return (+a[0]) * 3600000 + (+a[1]) * 60000 + (+a[2]) * 1000
        }

        list.sort(function (a, b) {
            return (hhmmssToMs(b["duration"]) + (Date.parse(b["startTime"])) < hhmmssToMs(a["duration"]) +
                (Date.parse(a["startTime"]))) ? 1 : ((hhmmssToMs(b["duration"]) +
                (Date.parse(b["startTime"])) > hhmmssToMs(a["duration"]) + (Date.parse(a["startTime"]))) ? -1 : 0)
        });
        return list
    }


    // Used by the Taskslist class to inform the Zookeeper class which task is currently chosen
    taskChosen(task) {
        this.setState({task_chosen: task, id_chosen: task.id});
    }

    // Used by the Taskslist class when finishing task - it sends a message to the server and once again downloads tasks
    async taskFinished() {
        const res = await fetch("http://localhost:8080/api/v1/tasks/delete/" + this.state.id_chosen,
            {method: "DELETE"})
        this.componentDidMount()
    }

    // If any task is chosen then this method calls the TaskInfo class to show more info in another window
    taskInfo() {
        if (this.state.id_chosen !== 0) {
            return (
                <TaskInfo
                    task={this.state.task_chosen}
                    id={this.state.id_chosen}
                    date={this.props.date}
                    finished={this.taskFinished.bind(this)}
                />
            )
        }
    }

    render() {
        return (
            <div>
                <div id="logInfo">
                    Zalogowano jako: opiekun {this.props.login}
                </div>

                <div id="tasksList">
                    {this.state.tasks.map(task => <TasksList
                            task={task}
                            IDChosen={this.state.id_chosen}
                            justClicked={this.taskChosen.bind(this)}
                        />
                    )}
                </div>

                {this.taskInfo()}

                <div style={{clear: "both"}}/>
            </div>
        );
    }
}

export default Zookeeper