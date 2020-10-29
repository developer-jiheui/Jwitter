export const initialState = {
    user :  null,
    token: null
}

const reducer =(state, action)=>{
    switch(action.type){
        case 'USER_LOGIN':
            console.log(action)
            return {...state,
            user:action.user,
        }
        case 'SET_TOKEN':
            return {...state,
            taken :action.token,
        }
        default:
            return state;
    }

}
export default reducer;