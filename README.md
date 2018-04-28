# Ibuy Application backend-(Java-REST APIs)

## REST APIS

..1)To get a customer detail - /getcust/{custname}  
....eg:- /getcust/cust1 gives you json response {"id":,"customerName":,"email":,"password":,"phone":}
..2)Sign up - /signup  
....pass the JSON input as follows {"customerName":,"email":,"password":,"phone":}
..3)Sign In - /signIn
....pass the JSON input as follows { "customerName": ,"password": }
..4)Get All products - /getAllProducts
....eg-/getAllProducts  gives you list of items in JSON Format {[{"id":1,"productName":"book","productprice":5,"weight":10,"barcode":"01020304","aisleNo":13},{"id":2,"productName":"Waterbottles","productprice":5,"weight":10,"barcode":"01020306","aisleNo":14},{"id":3,"productName":"Chips","productprice":3,"weight":5,"barcode":"01020301","aisleNo":1}]}
..5)Get Product details based on barcode scan - /getProduct/{barcode}
....eg-/getProduct/01020304 --gives JSON response {"id":1,"productName":"book","productprice":5,"weight":10,"aisleNo":13}
..6)Get Product Aisle Number -/getProductAilse/{productname}
....eg-/getProductAilse/book --gives JSON response {{"Aisleno":13}




