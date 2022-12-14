document.getElementById("view-Bus").addEventListener("click", getBus)



function getBus(event) {

    event.preventDefault();

    document.getElementById("tbody").innerHTML = null;
    getAllBus()
}


async function getAllBus() {
    try {

        let res = await fetch("http://localhost:8818/viewallbus", {
            method: "GET",
            // body:JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json"
            }
            // body:JSON.stringify(obj)
        })
        console.log(res)
        if (res.ok) {
            console.log("sucesss")
            let data = await res.json();

            // To get data from response   // user data
            // let userData=JSON.stringify(data)
            let d = JSON.stringify(data)


            console.log(d)


            appendData(data)



        } else {

            let data = await res.json();
            let error = JSON.stringify(data)

            let msg = JSON.parse(error);

            console.log(msg)
            alert(msg.message)
        }

    } catch (error) {
        console.log(error)
        alert("Connection failed")
       

    }


}




function appendData(data) {
data.map(function (el) {
let tr = document.createElement("tr");

let busId = document.createElement("td");
busId.innerText=el.busId

let busName = document.createElement("td");
busName.innerText=el.busName

let driverName = document.createElement("td");
driverName.innerText = el.driverName

let busType = document.createElement("td");
busType.innerText = el.busType

let departureTime = document.createElement("td");
departureTime.innerText = el.departureTime

let arrivalTime = document.createElement("td");
arrivalTime.innerText = el.arrivalTime

let seats = document.createElement("td");
seats.innerText = el.seats

let avilableSeats = document.createElement("td");
avilableSeats.innerText = el.availableSeats
    

tr.append(busId, busName, driverName,busType,departureTime,arrivalTime,seats,avilableSeats);

document.querySelector("#tbody").append(tr);
});
}