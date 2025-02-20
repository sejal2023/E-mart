using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("payment_details")]
[Index("Orderid", Name = "FKdfd34sf5c5xu3ralj3r11u3ih")]
public partial class PaymentDetail
{
    [Key]
    [Column("paymentid")]
    public int Paymentid { get; set; }

    [Column("payment_method")]
    [StringLength(255)]
    public string? PaymentMethod { get; set; }

    [Column("payment_status")]
    [StringLength(255)]
    public string? PaymentStatus { get; set; }

    [Column("orderid")]
    public int Orderid { get; set; }

    [ForeignKey("Orderid")]
    [InverseProperty("PaymentDetails")]
    public virtual Order Order { get; set; } = null!;
}
