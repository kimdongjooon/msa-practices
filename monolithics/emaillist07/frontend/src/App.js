import React, {useState, useEffect} from 'react';
import './assets/scss/App.scss';
import RegisterForm from './RegisterForm';
import Searchbar from './Searchbar';
import Emaillist from './Emaillist';
import update from 'react-addons-update';
// import data from './assets/json/data';

function App() {
    const [emails, setEmails] = useState(null);
    
    const addEmail = async (email) => {
        try{
            const response = await fetch('/api',{
                method:'post',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept':'application/json'
                },
                body: JSON.stringify(email)
            });
    
            if(!response.ok){
                throw new Error(`${response.status} ${response.statusText}`);
            }
    
            const json = await response.json();

            if(json.result !== 'success'){
                throw new Error(`${json.result} ${json.message}`);
            }
            
            const newEmails = [json.data, ...emails];
            setEmails(newEmails);

            // console.log(emails);
            // const addEmails = update(emails, { 
            //     $push:[{
            //         no:json.data.no,
            //         firstName:json.data.firstName,
            //         lastName:json.data.lastName,
            //         email:json.data.email
            //     }]
            // });
            // console.log(addEmails);
        }catch(e){
            console.error(e);
        }
        

    }
   
    const fetchEmails = async (keyword) => {
        try{
            const response = await fetch(`api?kw=${keyword? keyword : ''}`, {
                method: 'get',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept':'application/json'
                },
                body: null
    
            });
    
            if(!response.ok){
                throw new Error(`${response.status} ${response.statusText}`);
            }

            const json = await response.json();
            if(json.result !== 'success'){
                throw new Error(`${json.result} ${json.message}`);
            }

            
            setEmails(json.data);
        
        }catch(e){
            console.error(e);
        }
    }
    
    

    useEffect(() =>{
        fetchEmails();
        
    }, []);

   
    return (
        <div id={'App'}>
            <RegisterForm addEmail={addEmail}/>
            <Searchbar fetchEmails={fetchEmails}/>
            {emails == null?
                []:
            <Emaillist emails={emails}/>
            }
        </div>
            
    );
    
}
export{App};