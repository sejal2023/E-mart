using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("orders")]
[Index("Cartid", Name = "FKhwdcdaiq23cketwygk74e29c4")]
[Index("Userid", Name = "FKpnm1eeupqm4tykds7k3okqegv")]
public partial class Order
{
    [Key]
    [Column("orderid")]
    public int Orderid { get; set; }

    [Column("amount")]
    public double Amount { get; set; }

    [Column("order_date")]
    [MaxLength(6)]
    public DateTime? OrderDate { get; set; }

    [Column("payment_method", TypeName = "enum('COD','CREDIT_CARD','PAYPAL')")]
    public string? PaymentMethod { get; set; }

    [Column("status", TypeName = "enum('CANCELLED','DELIVERED','PENDING','PROCESSING','SHIPPED')")]
    public string? Status { get; set; }

    [Column("cartid")]
    public int Cartid { get; set; }

    [Column("userid")]
    public int Userid { get; set; }

    [ForeignKey("Cartid")]
    [InverseProperty("Orders")]
    public virtual Cart Cart { get; set; } = null!;

    [InverseProperty("Order")]
    public virtual ICollection<PaymentDetail> PaymentDetails { get; set; } = new List<PaymentDetail>();

    [ForeignKey("Userid")]
    [InverseProperty("Orders")]
    public virtual User User { get; set; } = null!;
}
