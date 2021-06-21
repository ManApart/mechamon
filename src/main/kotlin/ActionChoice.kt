import actions.Action

class ActionChoice(val action: Action, val possible: Boolean) {
    constructor(action: Action, availableAp: Int) : this(action, action.cost <= availableAp)
}
