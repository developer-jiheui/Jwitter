export const initialState = {
    user :  null,
    token: null
}

const reducer =(state, action)=>{
    switch(action.type){
        case 'SET_USER':
            console.log(action)
            return {...state,
            user:action.use,
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