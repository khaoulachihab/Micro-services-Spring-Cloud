import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit{

  bills:any;
  customerId!:number;

  constructor(private http:HttpClient,private router:Router,private route:ActivatedRoute){
    this.customerId=route.snapshot.params["customerID"];
  }
  ngOnInit(): void{
    this.http.get("http://localhost:8889/BILLING-SERVICE/bills/search/byCustomerID?projection=fullBill&customerID="+this.customerId).subscribe({
      next:(data)=>{
        console.log(data)
        this.bills=data;
       
      },
      error:(error)=>{}
    });


}
getBillDetails(b:any){
  this.router.navigateByUrl("/BillDetails/"+b.id);
}
}
